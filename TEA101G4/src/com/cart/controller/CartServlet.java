package com.cart.controller;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.member.model.MemberVO;

import redis.clients.jedis.Jedis;

@WebServlet("/front-end/cart/cart.do")
public class CartServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1353352949593172597L;
	int a = 1;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("---CartServlet_doPost---" + (a++));
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");
		Gson gson = new Gson();// Google的對JSON處理（載jar檔即可使用）

		List<CartVO> buylist = (List<CartVO>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
		System.out.println("action=" + action);
		System.out.println("buylist_size=" + buylist);

		MemberVO userVO = (MemberVO) session.getAttribute("userVO");
		String memberid = null;
		if (userVO != null) {
			memberid = userVO.getMemberid();
			System.out.println("取得登入的memberid=>" + memberid);

			if (action.equals("getMemberCartRedis")) {
				// json轉為帶泛型的list
				if (jedis.get(memberid) != null) {
					List<CartVO> reMemberCartList = gson.fromJson(jedis.get(memberid), new TypeToken<List<CartVO>>() {
					}.getType());
//					System.out.println("reMemberCartList=>" + reMemberCartList.toString());
					if (buylist == null) {
						buylist = reMemberCartList;
//						System.out.println("buylist:" + buylist.toString());
					}
				}
			}
		}
		System.out.println("ping2:" + jedis.ping());
		if (!action.equals("CHECKOUT")) {
//			Boolean refresh = false;
			// 刪除購物車中的商品
			if (action.equals("DELETE")) { // （DELETE是來自Cart.jsp)
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.remove(d);
			}
			// 新增商品至購物車
			else if (action.equals("ADD")) { // （ADD是來自one_product.jsp)
				
				boolean match = false;
				// 取得後來新增的商品
				CartVO acart = getCartVO(req);
				System.out.println("acart=" + acart.toString());
				// 新增第一個商品到購物車時
				if (buylist == null) { // buylist由session取出時為空值時
					buylist = new Vector<CartVO>();
					buylist.add(acart); // 新增
				} else {
					for (CartVO cart : buylist) {
						// 若新增的商品與購物車商品一樣時
						if ((cart.getSpecid().equals(acart.getSpecid()))) {
							cart.setQuantity(cart.getQuantity() + acart.getQuantity());
							match = true;
						} // end of if name matches
					} // end of for
					// 若新增的與購物車不同時
					if (!match)
						buylist.add(acart);
				}
			}
			session.setAttribute("shoppingcart", buylist); // buylist經ＡＤＤ區塊處理已有值，放入session scope內

			String url = "/front-end/cart/Cart.jsp"; // 該區域（上面）請求完成後，會回到Shop頁面
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			System.out.println("RequestDispatcher");
//				
			String gsonBuylist = gson.toJson(buylist);// 帶泛型的list轉化為json
			System.out.println("帶泛型的buylist轉化為json=>" + gsonBuylist);
			if (memberid != null) {
				jedis.set(memberid, gsonBuylist);
				System.out.println("(JSON)memberid(set):" + memberid + ";value:" + jedis.get(memberid));
			}
		}
		// 結帳，計算購物車價錢總數
		else if (action.equals("CHECKOUT")) { // （CHECKOUT是來自Cart.jsp)
			
			Integer total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				CartVO order = buylist.get(i);
				Integer price = order.getPrice();
				Integer quantity = order.getQuantity();
				total += (price * quantity);
			}
//			
//			Integer total = buylist.stream()
//					.map(b -> b.getPrice() * b.getQuantity())
//					.sum();
//			
			String amount = String.valueOf(total); // 將上面總額存成字串
			req.getSession().setAttribute("amount", amount); // 此部分就是把金額放在Checkout.jsp，只在Checkout.jsp看到

			String url = "/front-end/cart/Checkout.jsp"; // 該區域請求完成後，會回到Shop頁
			RequestDispatcher rd = req.getRequestDispatcher(url); //
			rd.forward(req, res);// 轉(forward)回 url = "/Checkout.jsp" 進到結帳畫面
			if (memberid != null) {
				String gsonBuylist = gson.toJson(buylist);
				jedis.del(memberid.toString(), gsonBuylist);
				System.out.println("(JSON)memberid(del):" + memberid + ";value:" + jedis.get(memberid));
			}
		}
		jedis.close();
		System.out.println("CartServlet-------end");
	}

	private CartVO getCartVO(HttpServletRequest req) {
//		System.out.println("---getCartVO---");
		String memberid = req.getParameter("memberid");
//		System.out.println("memberid__:" + memberid);
		String specid = req.getParameter("specid");
//		System.out.println("specid__:" + specid);
		String specific = req.getParameter("specific");
//		System.out.println("specific__:" + specific);

		String productid = req.getParameter("productid");
//		System.out.println("productid=" + productid);
//byte[] photo1 = req.getParameter("photo1");
		String brandname = req.getParameter("brandname");
		System.out.println("brandname=" + brandname);
		String productname = req.getParameter("productname");
//		System.out.println("productname=" + productname);
		Integer quantity = new Integer(req.getParameter("quantity")).intValue();
//		System.out.println("quantity=" + quantity);
		Integer price = new Integer(req.getParameter("price")).intValue();
//		System.out.println("price=" + price);

		CartVO cartVO = new CartVO();
		cartVO.setMemberid(memberid);
		cartVO.setSpecid(specid);
		cartVO.setProductid(productid);
//cartVO.setPhoto1(photo1);
		cartVO.setBrandname(brandname);
		cartVO.setProductname(productname);
		cartVO.setQuantity(quantity);
		cartVO.setPrice(price);
		cartVO.setSpecific(specific);

//		System.out.println("---getCartVO---end---");
		return cartVO;
	}
}
