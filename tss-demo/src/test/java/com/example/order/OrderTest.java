package com.example.order;

import org.graphwalker.core.machine.ExecutionContext;
import org.unibuc.OrderModel;
import org.unibuc.OrderService;
import org.graphwalker.java.annotation.GraphWalker;

import static org.junit.Assert.assertEquals;

@GraphWalker(value = "random(edge_coverage(100))", start = "v_Start")
public class OrderTest extends ExecutionContext implements OrderModel {

    private final OrderService service = new OrderService();

    @Override
    public void v_Start() {
        assertEquals(OrderService.State.NONE, service.getState());
    }

    @Override
    public void v_RequestCreated() {
        assertEquals(OrderService.State.REQUEST_CREATED, service.getState());
    }

    @Override
    public void v_Approved() {
        assertEquals(OrderService.State.APPROVED, service.getState());
    }

    @Override
    public void v_Rejected() {
        assertEquals(OrderService.State.REJECTED, service.getState());
    }

    @Override
    public void v_OrderedFromSupplier() {
        assertEquals(OrderService.State.ORDERED_SUPPLIER, service.getState());
    }

    @Override
    public void v_DeliveredToStore() {
        assertEquals(OrderService.State.DELIVERED_STORE, service.getState());
    }

    @Override
    public void v_PickedUpByCustomer() {
        assertEquals(OrderService.State.PICKED_UP, service.getState());
    }

    @Override
    public void e_CreateRequest() {
        service.createRequest();
    }

    @Override
    public void e_ApproveRequest() {
        service.approveRequest();
    }

    @Override
    public void e_RejectRequest() {
        service.rejectRequest();
    }

    @Override
    public void e_OrderSupplier() {
        service.orderFromSupplier();
    }

    @Override
    public void e_ReceiveFromSupplier() {
        service.receiveFromSupplier();
    }

    @Override
    public void e_Pickup() {
        service.pickup();
    }

    @Override
    public void e_Reset() {
        service.reset();
    }
}
