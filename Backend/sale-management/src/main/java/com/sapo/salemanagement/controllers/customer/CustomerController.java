package com.sapo.salemanagement.controllers.customer;

import com.sapo.salemanagement.controllers.BaseController;
import com.sapo.salemanagement.dto.PagedResponseObject;
import com.sapo.salemanagement.dto.ResponseObject;
import com.sapo.salemanagement.dto.customer.CustomerSpendingDTO;
import com.sapo.salemanagement.dto.customer.FeedbackDTO;
import com.sapo.salemanagement.dto.orderdtos.OrderListByCustomer;
import com.sapo.salemanagement.models.Customer;
import com.sapo.salemanagement.models.Feedback;
import com.sapo.salemanagement.services.customer.CustomerServices;
import com.sapo.salemanagement.services.customer.FeedbackService;
import com.sapo.salemanagement.services.order.OrderService;
import com.sapo.salemanagement.services.SMSService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class CustomerController extends BaseController {
    private final CustomerServices customerServices;
    private final FeedbackService feedbackService;
    private final OrderService orderService;
    private final SMSService smsService;

    public CustomerController(CustomerServices customerServices, FeedbackService feedbackService, OrderService orderService, SMSService smsService) {
        this.customerServices = customerServices;
        this.feedbackService = feedbackService;
        this.orderService = orderService;
        this.smsService = smsService;
    }

    //lấy danh sách khách hàng
    @GetMapping("/customer")
    public ResponseEntity<PagedResponseObject> getAllCustomer(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                 @RequestParam(value = "size", defaultValue = "10") int size) {

        long totalItems = customerServices.countCustomer();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        List<Customer> customers = customerServices.getAllCustomer(page, size);
        return ResponseEntity.ok(PagedResponseObject.builder()
                        .page(page)
                        .perPage(size)
                        .totalItems(totalItems)
                        .totalPages(totalPages)
                        .responseCode(200)
                        .message("Success")
                        .data(customers)
                        .build());
    }

    //lấy danh sách khách hàng theo chi tiêu
    @GetMapping("/customer/spending")
    public ResponseEntity<PagedResponseObject> getAllCustomerBySpending(@RequestParam(value = "page", defaultValue = "1") int page,
                                                              @RequestParam(value = "size", defaultValue = "10") int size) {

        long totalItems = customerServices.countCustomer();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        List<CustomerSpendingDTO> customers = customerServices.getAllCustomerBySpending(page, size);
        return ResponseEntity.ok(PagedResponseObject.builder()
                .page(page)
                .perPage(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .responseCode(200)
                .message("Success")
                .data(customers)
                .build());
    }

    //lây chi tiết khách hàng
    @GetMapping("/customer/{id}")
    public ResponseEntity<ResponseObject> getDetailCustomer(@PathVariable("id") int customerId) {
        Customer customer = customerServices.getDetailCustomer(customerId);
        return ResponseEntity.ok(ResponseObject.builder()
                .responseCode(200)
                .message("Success")
                .data(customer)
                .build());
    }

    @GetMapping("/customer/count")
    public ResponseEntity<ResponseObject> getCountCustomer() {
        long countCustomer = customerServices.countCustomer();
        return ResponseEntity.ok(ResponseObject.builder()
                .responseCode(200)
                .message("Success")
                .data(countCustomer)
                .build());
    }

    // Xử lý yêu cầu GET để tìm khách hàng dựa trên tên và số điện thoại
    @GetMapping("/customer/search")
    public ResponseEntity<ResponseObject> searchCustomers(@RequestParam("searchTerm") String searchTerm) {
        List<Customer> listCustomer = customerServices.searchCustomers1(searchTerm);
        ResponseObject responseObject = ResponseObject.builder()
                .responseCode(200)
                .message("Success")
                .data(listCustomer)
                .build();

        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/customer/search_spending")
    public ResponseEntity<ResponseObject> searchCustomersSpending(@RequestParam("searchTerm") String searchTerm) {
        List<CustomerSpendingDTO> listCustomer = customerServices.searchCustomers(searchTerm);
        ResponseObject responseObject = ResponseObject.builder()
                .responseCode(200)
                .message("Success")
                .data(listCustomer)
                .build();

        return ResponseEntity.ok(responseObject);
    }

    //tạo mới một khách hàng
//    @PostMapping("/customer")
//    public ResponseEntity<ResponseObject> createCustomer(@Valid @RequestBody Customer customer){
//        Customer newCustomer =  customerServices.createCustomer(customer);
//        String convertedPhoneNumber = "+84" + customer.getPhone().substring(1);
//        smsService.sendSMS(convertedPhoneNumber, "Chúc mừng bạn đã trở thành khách hàng thân thiết của SAPO");
//        return ResponseEntity.ok(ResponseObject.builder()
//                .responseCode(200)
//                .message("Success")
//                .data(newCustomer)
//                .build());
//    }

    @PostMapping("/customer")
    public ResponseEntity<ResponseObject> createCustomer(@Valid @RequestBody Customer customer) {
        Customer newCustomer = null;
        try {
            newCustomer = customerServices.createCustomer(customer);
        } catch (Exception e) {
            // Xử lý lỗi khi tạo người dùng
            System.out.println(e);
        }

        try {
            String convertedPhoneNumber = "+84" + customer.getPhone().substring(1);
            smsService.sendSMS(convertedPhoneNumber, "Chúc mừng bạn đã trở thành khách hàng thân thiết của SAPO");
        } catch (Exception e) {
            System.out.println(e);
        }

        if (newCustomer != null) {
            return ResponseEntity.ok(ResponseObject.builder()
                    .responseCode(200)
                    .message("Success")
                    .data(newCustomer)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseObject.builder()
                            .responseCode(500)
                            .message("Error creating customer")
                            .data(null)
                            .build());
        }
    }


    //cập nhât một khách hàng
    @PutMapping("/customer/{id}")
    public ResponseEntity<ResponseObject> updateCustomer(@PathVariable("id") int customerId, @RequestBody Customer customer){
        Customer newCustomer = customerServices.updateCustomer(customerId, customer);
        return ResponseEntity.ok(ResponseObject.builder()
                .responseCode(200)
                .message("Success")
                .data(newCustomer)
                .build());
    }

    //xóa khách hàng
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<ResponseObject> deleteCustomer(@PathVariable("id") int customerId) {
        customerServices.deleteCustomerById(customerId);
        return ResponseEntity.ok(ResponseObject.builder()
                .responseCode(200)
                .message("Deleted Customer")
                .data(null)
                .build());
    }

    //Feedback
    @GetMapping("/customer/{customerId}/feedback")
    public ResponseEntity<ResponseObject> getFeedbackList(@PathVariable int customerId) {
        List<Feedback> feedbackList = feedbackService.getFeedbackList(customerId);
        ResponseObject responseObject = ResponseObject.builder()
                .responseCode(200)
                .message("Success")
                .data(feedbackList)
                .build();
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/customer/feedback")
    public ResponseEntity<PagedResponseObject> getAllFeedback(@RequestParam(value = "page", defaultValue = "1") int page,
                                                              @RequestParam(value = "size", defaultValue = "10") int size) {

        long totalItems = feedbackService.countFeedback();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        List<Feedback> feedbackList = feedbackService.getAllFeedback(page, size);
        return ResponseEntity.ok(PagedResponseObject.builder()
                .page(page)
                .perPage(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .responseCode(200)
                .message("Success")
                .data(feedbackList)
                .build());
    }

    // Xử lý yêu cầu GET để tìm khách hàng dựa trên tên và số điện thoại
    @GetMapping("/customer/feedback/search")
    public ResponseEntity<ResponseObject> searchCustomersFeedback(@RequestParam("searchTerm") String searchTerm) {
        List<Feedback> listFeedback = feedbackService.searchFeedbacks(searchTerm);
        ResponseObject responseObject = ResponseObject.builder()
                .responseCode(200)
                .message("Success")
                .data(listFeedback)
                .build();

        return ResponseEntity.ok(responseObject);
    }

    @PostMapping("/customer/feedback")
    public ResponseEntity<ResponseObject> createFeedback(@RequestBody @Valid FeedbackDTO feedbackDTO, @AuthenticationPrincipal UserDetails userDetails) {
        String staffPhone = userDetails.getUsername();
        feedbackService.createFeedback(feedbackDTO, staffPhone);
        return ResponseEntity.ok(ResponseObject.builder()
                .message("success")
                .data(feedbackDTO)
                .responseCode(200)
                .build());
    }

    @PutMapping("/customer/feedback/{id}")
    public ResponseEntity<ResponseObject> updateFeedback(@PathVariable("id") int feedbackId, @RequestBody @Valid FeedbackDTO feedbackDTO, @AuthenticationPrincipal UserDetails userDetails){
        String staffPhone = userDetails.getUsername();
        Feedback updateFeedback = feedbackService.updateFeedback(feedbackId, feedbackDTO, staffPhone);
        return ResponseEntity.ok(ResponseObject.builder()
                .responseCode(200)
                .message("Success update feedback")
                .data(updateFeedback)
                .build());
    }

    //xóa khách hàng
    @DeleteMapping("/customer/feedback/{id}")
    public ResponseEntity<ResponseObject> deleteFeedback(@PathVariable("id") int feedbackId) {
        feedbackService.deleteFeedbackById(feedbackId);
        return ResponseEntity.ok(ResponseObject.builder()
                .responseCode(200)
                .message("Deleted Feedback")
                .data(null)
                .build());
    }

    //Order
    @GetMapping("/customer/{customerId}/order")
    public ResponseEntity<ResponseObject> getOrderListByCustomerId(@PathVariable("customerId") int customerId) {
        List<OrderListByCustomer> orderList = orderService.getOrderListByCustomerId(customerId);
        ResponseObject responseObject = ResponseObject.builder()
                .responseCode(200)
                .message("Success")
                .data(orderList)
                .build();
        return ResponseEntity.ok(responseObject);
    }

}
