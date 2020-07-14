package com.delivery.HomeDelivery.HD.controller;

import com.delivery.HomeDelivery.HD.entity.MemberShip;
import com.delivery.HomeDelivery.HD.entity.PostOrder;
import com.delivery.HomeDelivery.HD.entity.User;
import com.delivery.HomeDelivery.HD.service.MemberShipService;
import com.delivery.HomeDelivery.HD.service.PostOrderService;
import com.delivery.HomeDelivery.HD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/postorder")
public class PostOrderController {
    @Autowired
    private MemberShipService ms;
    @Autowired
    private UserService us;
    @Autowired
    private PostOrderService ps;
    @GetMapping("/orderlist")
    public String toPostorder(HttpSession session, Model m) {
        int userid = (int) session.getAttribute("userid");
        User user = us.findUserById(userid);
        if (String.valueOf(user.getRole()).equals("MEMBER")) {
            MemberShip member = ms.findMemberShipById(userid);
            List<PostOrder> postOrders = member.getPostOrders();
            m.addAttribute("postOrders",postOrders);
        }

        return "postorderlist";
    }
    @PostMapping("/status")
    public String deliveryStatus(String trackcode, Model m) {
        PostOrder postOrder = ps.findPostOrderByNumber(trackcode);
        if (postOrder == null) {
            m.addAttribute("msg", "此订单号无效");
            return "postorderlist";
        }
        else {
            switch (postOrder.getCount()) {
                case 1 : postOrder.setStatus(PostOrder.Status.SHIPPED); break;
                case 2 : postOrder.setStatus(PostOrder.Status.SHIPPING); break;
                case 3 : postOrder.setStatus(PostOrder.Status.RECEIVED); break;
            }
            postOrder.setCount(postOrder.getCount() + 1);
            ps.updatePostOrder(postOrder);
            m.addAttribute("postorder",postOrder);


           /* new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                postOrder.setStatus(PostOrder.Status.SHIPPED);
                ps.updatePostOrder(postOrder);
                m.addAttribute("postorder",postOrder);
            });*/


        }
        return "process";
    }

}
