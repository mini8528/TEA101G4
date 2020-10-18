package com.cartClass.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cartClass.model.CartClassVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.member.model.MemberVO;

import redis.clients.jedis.Jedis;

@WebServlet("/front-end/cart/cartClass.do")
public class CartClassServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("==========================================");
		System.out.println("=====    è³¼ç‰©è»?     Cart Class Servlet       ===");
		System.out.println("==========================================");
		
		
		System.out.println("memberid__:" + req.getParameter("memberid") );
		System.out.println("coachClassID:" + req.getParameter("coachClassID"));
		System.out.println("className:" + req.getParameter("className"));
		System.out.println("memberid_Coach=" + req.getParameter("memberid_Coach"));
		System.out.println("startTime=" + req.getParameter("startTime"));
		System.out.println("price=" + req.getParameter("price"));
		System.out.println("address=" + req.getParameter("address"));
		System.out.println("quantity=" + req.getParameter("quantity"));

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");
		Gson gson = new Gson();// Google??„å?JSON??•ç?†ï?ˆè?‰jaræª”å³?¯ä½¿ç”¨ï¼?

		List<CartClassVO> buylist = (List<CartClassVO>) session.getAttribute("shoppingCartClass");
		String action = req.getParameter("action");
		System.out.println();
		System.out.println("action = " + action);// action=ADD
		System.out.println("buylist_size=" + buylist);// buylist_size=null
		MemberVO userVO = (MemberVO) session.getAttribute("userVO");
		String memberid = null;
		if (userVO != null) {
			memberid = userVO.getMemberid();
			System.out.println();// ??–å?—ç™»?…¥??„memberid=>M001
			System.out.println("=== icon è³¼ç‰©è»Šå?–å?—ç™»?…¥??„memberid => " + memberid +" ===");
			System.out.println("action 1" + action);
			if (action.equals("getMemberCartClassRedis")) {
				
				// jsonè½‰ç‚ºå¸¶æ?›å?‹ç?„list
				if (jedis.get(memberid) != null) {
					List<CartClassVO> reMemberCartClassList = gson.fromJson(jedis.get(memberid),
							new TypeToken<List<CartClassVO>>() {
							}.getType());
					System.out.println("reMemberCartClassList=>" + reMemberCartClassList.toString());
					if (buylist == null) {
						buylist = reMemberCartClassList;
						System.out.println("buylist:" + buylist.toString());
					}
				}
			}

			System.out.println("ping2:" + jedis.ping());// ping2:PONG
			if (!action.equals("CHECKOUT")) {
				System.out.println("action 2" + action);
				// ?ˆª?™¤è³¼ç‰©è»Šä¸­??„å?†å??
				if (action.equals("DELETE")) { // ï¼ˆDELETE?˜¯ä¾†è‡ªCartClass.jsp)
					String del = req.getParameter("del");
					int d = Integer.parseInt(del);
					buylist.remove(d);
				}
				// ?–°å¢å?†å?è‡³è³¼ç‰©è»?
				else if (action.equals("ADD")) { // ï¼ˆADD?˜¯ä¾†è‡ªone_product.jsp)

					boolean match = false;
					// ??–å?—å?Œä?†æ–°å¢ç?„å?†å??
					CartClassVO acartClass = getCartClassVO(req);
					System.out.println();
					System.out.println("=== acartClass=" + acartClass.toString());//
					// ?–°å¢ç¬¬ä¸??‹å?†å?åˆ°è³¼ç‰©è»Šæ??
					if (buylist == null) { // buylist?”±session??–å‡º??‚ç‚ºç©ºå?¼æ??
						buylist = new Vector<CartClassVO>();
						buylist.add(acartClass); // ?–°å¢?
						System.out.println("?–°??„è³¼?‰©è»?");
					} else {
						for (CartClassVO cartClass : buylist) {
							// ?‹¥?–°å¢ç?„å?†å?è?‡è³¼?‰©è»Šå?†å?ä?æ¨????
							if ((cartClass.getCoachClassID().equals(acartClass.getCoachClassID()))) {
//							if ((cart.getProductid().equals(acart.getProductid())
//									&& (cart.getSpecific().equals(acart.getSpecific())))) {
//								cartClass.setQuantity(cartClass.getQuantity() + acartClass.getQuantity());
								System.out.println();
								System.out.println("è³¼ç‰©è»Šé?è??");
								match = true;
							} // end of if name matches
						} // end of for
							// ?‹¥?–°å¢ç?„è?‡è³¼?‰©è»Šä?å?Œæ??
						if (!match)
							System.out.println();
						System.out.println("è¼¸å…¥æª”æ?ˆé?? CartClassVO ");
							buylist.add(acartClass);
					}
				}
				System.out.println();
				System.out.println("action 3" + action);
				session.setAttribute("shoppingCartClass", buylist); // buylistç¶“ï¼¡ï¼¤ï¼¤??å¡Šè?•ç?†å·²??‰å?¼ï?Œæ”¾?…¥session scope?…§

				String gsonBuylist = gson.toJson(buylist);// å¸¶æ?›å?‹ç?„listè½‰å?–ç‚ºjson
				System.out.println("å¸¶æ?›å?‹ç?„buylistè½‰å?–ç‚ºjson=>" + gsonBuylist);
				jedis.set(memberid.toString(), gsonBuylist);
				System.out.println("(JSON)memberid(before):" + memberid + ";value:" + jedis.get(memberid));

				String url = "/front-end/cart/CartClass.jsp"; // è©²å???Ÿï?ˆä?Šé¢ï¼‰è?‹æ?‚å?Œæ?å?Œï?Œæ?ƒå?åˆ°Shop??é¢
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				System.out.println("RequestDispatcher");
//				
				jedis.del(memberid.toString(), gsonBuylist);
				System.out.println("(JSON)memberid(after):" + memberid + ";value:" + jedis.get(memberid));
				jedis.close();
			}
			// çµå¸³ï¼Œè?ˆç?—è³¼?‰©è»Šåƒ¹?Œ¢ç¸½æ•¸
			else if (action.equals("CHECKOUT")) { // ï¼ˆCHECKOUT?˜¯ä¾†è‡ªCartClass.jsp)
				System.out.println("CHECKOUT");
				Integer total = 0;
				for (int i = 0; i < buylist.size(); i++) {
					CartClassVO order = buylist.get(i);
					Integer price = order.getPrice();
					total += price;
				}
				String amount = String.valueOf(total); // å°‡ä?Šé¢ç¸½é?å?˜æ?å?—ä¸²
				req.setAttribute("amount", amount); // æ­¤éƒ¨??†å°±?˜¯??Šé?‘é?æ”¾?œ¨Checkout.jspï¼Œåª?œ¨Checkout.jsp??‹åˆ°
				String url = "/front-end/cart/CheckoutClass.jsp"; // è©²å???Ÿè?‹æ?‚å?Œæ?å?Œï?Œæ?ƒå?åˆ°Shop???
				RequestDispatcher rd = req.getRequestDispatcher(url); //
				rd.forward(req, res);// è½?(forward)??? url = "/Checkout.jsp" ?²åˆ°çµå¸³?•«?¢
			}
			System.out.println("CartClassServlet-------end");
		}
			else {
				System.out.println("?œª?™»?…¥å°±æƒ³?–½?”¨è³¼ç‰©è»? > å°å?‘ç™»?…¥??é¢");
			RequestDispatcher rd = req.getRequestDispatcher("/front-end/login.jsp");
			rd.forward(req, res);
		}
	}

	private CartClassVO getCartClassVO(HttpServletRequest req) {
		System.out.println("---getCartVO---");
		
		
		String memberid = req.getParameter("memberid");
		

		String coachClassID = req.getParameter("coachClassID");

		String className = req.getParameter("className");

		String memberid_Coach = req.getParameter("memberid_Coach");

		Integer price = new Integer(req.getParameter("price")).intValue();

		String address = req.getParameter("address");

		Integer quantity = new Integer(req.getParameter("quantity")).intValue();

//		byte[] photo1 = req.getParameter("photo1");

		Timestamp startTime = Timestamp.valueOf(req.getParameter("startTime"));

		CartClassVO cartClassVO = new CartClassVO();
		cartClassVO.setMemberid(memberid);
		cartClassVO.setCoachClassID(coachClassID);
		cartClassVO.setClassName(className);
//		cartVO.setPhoto1(photo1);
		cartClassVO.setMemberid_Coach(memberid_Coach);
		cartClassVO.setStartTime(startTime);
		cartClassVO.setPrice(price);
		cartClassVO.setAddress(address);
		cartClassVO.setQuantity(quantity);

		System.out.println("---getCartClassVO---end---");
		return cartClassVO;
	}
}
