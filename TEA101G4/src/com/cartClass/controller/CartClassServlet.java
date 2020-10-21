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

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");
		Gson gson = new Gson();

		List<CartClassVO> buylist = (List<CartClassVO>) session.getAttribute("shoppingCartClass");
		String action = req.getParameter("action");
		MemberVO userVO = (MemberVO) session.getAttribute("userVO");
		String memberid = null;
		if (userVO != null) {
			memberid = userVO.getMemberid();
			if (action.equals("getMemberCartClassRedis")) {
				
				if (jedis.get(memberid) != null) {
					List<CartClassVO> reMemberCartClassList = gson.fromJson(jedis.get(memberid),
							new TypeToken<List<CartClassVO>>() {
							}.getType());
					if (buylist == null) {
						buylist = reMemberCartClassList;
					}
				}
			}

			if (!action.equals("CHECKOUT")) {
				if (action.equals("DELETE")) { 
					String del = req.getParameter("del");
					int d = Integer.parseInt(del);
					buylist.remove(d);
				}
				else if (action.equals("ADD")) {
					boolean match = false;
					CartClassVO acartClass = getCartClassVO(req);
					if (buylist == null) {
						buylist = new Vector<CartClassVO>();
						buylist.add(acartClass); // ?���?
					} else {
						for (CartClassVO cartClass : buylist) {
							if ((cartClass.getCoachClassID().equals(acartClass.getCoachClassID()))) {
								match = true;
							} 
						} 
						if (!match)
							buylist.add(acartClass);
					}
				}
				session.setAttribute("shoppingCartClass", buylist); 

				String gsonBuylist = gson.toJson(buylist);
				jedis.set(memberid.toString(), gsonBuylist);

				String url = "/front-end/cartClass/CartClass.jsp"; 
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
//				
				jedis.del(memberid.toString(), gsonBuylist);
				jedis.close();
			}
			else if (action.equals("CHECKOUT")) { // （CHECKOUT?��來自CartClass.jsp)
				Integer total = 0;
				for (int i = 0; i < buylist.size(); i++) {
					CartClassVO order = buylist.get(i);
					Integer price = order.getPrice();
					total += price;
				}
				String amount = String.valueOf(total); 
				req.setAttribute("amount", amount); 
				String url = "/front-end/cartClass/CheckoutClass.jsp"; 
				RequestDispatcher rd = req.getRequestDispatcher(url); 
				rd.forward(req, res);
			}
		}
			else {
				
				System.out.println("cart servlet userVO = "+userVO);
				
			RequestDispatcher rd = req.getRequestDispatcher("/front-end/login.jsp");
			rd.forward(req, res);
		}
	}

	private CartClassVO getCartClassVO(HttpServletRequest req) {
		
		
		String memberid = req.getParameter("memberid");
		

		String coachClassID = req.getParameter("coachClassID");

		String className = req.getParameter("className");

		String memberid_Coach = req.getParameter("memberid_Coach");

		Integer price = new Integer(req.getParameter("price")).intValue();

		String address = req.getParameter("address");

		Integer quantity = new Integer(req.getParameter("quantity")).intValue();

//		byte[] photo1 = req.getParameter("photo1");

		Timestamp startTime = Timestamp.valueOf(req.getParameter("startTime"));
		
		String coachName = req.getParameter("coachName");

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
		cartClassVO.setCoachName(coachName);

		return cartClassVO;
	}
}
