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
		System.out.println("=====    購物�?     Cart Class Servlet       ===");
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
		Gson gson = new Gson();// Google??��?�JSON??��?��?��?�jar檔即?��使用�?

		List<CartClassVO> buylist = (List<CartClassVO>) session.getAttribute("shoppingCartClass");
		String action = req.getParameter("action");
		System.out.println();
		System.out.println("action = " + action);// action=ADD
		System.out.println("buylist_size=" + buylist);// buylist_size=null
		MemberVO userVO = (MemberVO) session.getAttribute("userVO");
		String memberid = null;
		if (userVO != null) {
			memberid = userVO.getMemberid();
			System.out.println();// ??��?�登?��??�memberid=>M001
			System.out.println("=== icon 購物車�?��?�登?��??�memberid => " + memberid +" ===");
			System.out.println("action 1" + action);
			if (action.equals("getMemberCartClassRedis")) {
				
				// json轉為帶�?��?��?�list
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
				// ?��?��購物車中??��?��??
				if (action.equals("DELETE")) { // （DELETE?��來自CartClass.jsp)
					String del = req.getParameter("del");
					int d = Integer.parseInt(del);
					buylist.remove(d);
				}
				// ?��增�?��?�至購物�?
				else if (action.equals("ADD")) { // （ADD?��來自one_product.jsp)

					boolean match = false;
					// ??��?��?��?�新增�?��?��??
					CartClassVO acartClass = getCartClassVO(req);
					System.out.println();
					System.out.println("=== acartClass=" + acartClass.toString());//
					// ?��增第�??��?��?�到購物車�??
					if (buylist == null) { // buylist?��session??�出??�為空�?��??
						buylist = new Vector<CartClassVO>();
						buylist.add(acartClass); // ?���?
						System.out.println("?��??�購?���?");
					} else {
						for (CartClassVO cartClass : buylist) {
							// ?��?��增�?��?��?��?�購?��車�?��?��?�????
							if ((cartClass.getCoachClassID().equals(acartClass.getCoachClassID()))) {
//							if ((cart.getProductid().equals(acart.getProductid())
//									&& (cart.getSpecific().equals(acart.getSpecific())))) {
//								cartClass.setQuantity(cartClass.getQuantity() + acartClass.getQuantity());
								System.out.println();
								System.out.println("購物車�?��??");
								match = true;
							} // end of if name matches
						} // end of for
							// ?��?��增�?��?�購?��車�?��?��??
						if (!match)
							System.out.println();
						System.out.println("輸入檔�?��?? CartClassVO ");
							buylist.add(acartClass);
					}
				}
				System.out.println();
				System.out.println("action 3" + action);
				session.setAttribute("shoppingCartClass", buylist); // buylist經ＡＤＤ??塊�?��?�已??��?��?�放?��session scope?��

				String gsonBuylist = gson.toJson(buylist);// 帶�?��?��?�list轉�?�為json
				System.out.println("帶�?��?��?�buylist轉�?�為json=>" + gsonBuylist);
				jedis.set(memberid.toString(), gsonBuylist);
				System.out.println("(JSON)memberid(before):" + memberid + ";value:" + jedis.get(memberid));

				String url = "/front-end/cart/CartClass.jsp"; // 該�???��?��?�面）�?��?��?��?��?��?��?��?�到Shop??�面
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				System.out.println("RequestDispatcher");
//				
				jedis.del(memberid.toString(), gsonBuylist);
				System.out.println("(JSON)memberid(after):" + memberid + ";value:" + jedis.get(memberid));
				jedis.close();
			}
			// 結帳，�?��?�購?��車價?��總數
			else if (action.equals("CHECKOUT")) { // （CHECKOUT?��來自CartClass.jsp)
				System.out.println("CHECKOUT");
				Integer total = 0;
				for (int i = 0; i < buylist.size(); i++) {
					CartClassVO order = buylist.get(i);
					Integer price = order.getPrice();
					total += price;
				}
				String amount = String.valueOf(total); // 將�?�面總�?��?��?��?�串
				req.setAttribute("amount", amount); // 此部??�就?��??��?��?�放?��Checkout.jsp，只?��Checkout.jsp??�到
				String url = "/front-end/cart/CheckoutClass.jsp"; // 該�???��?��?��?��?��?��?��?��?�到Shop???
				RequestDispatcher rd = req.getRequestDispatcher(url); //
				rd.forward(req, res);// �?(forward)??? url = "/Checkout.jsp" ?�到結帳?��?��
			}
			System.out.println("CartClassServlet-------end");
		}
			else {
				System.out.println("?��?��?��就想?��?��購物�? > 導�?�登?��??�面");
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
