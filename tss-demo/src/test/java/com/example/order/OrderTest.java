package com.example.order;

import org.graphwalker.core.machine.ExecutionContext;
import org.unibuc.order.OrderModel;
import org.unibuc.order.OrderService;
import org.graphwalker.java.annotation.GraphWalker;
import org.unibuc.order.State;

import static org.junit.Assert.assertEquals;

@GraphWalker(value = "OrderServiceTest", start = "v_Start")
public class OrderTest extends ExecutionContext implements OrderModel {

    private final OrderService service = new OrderService();

    @Override
    public void v_Start() {
        System.out.print("-> (v_Start) ");
        assertEquals(State.NONE, service.getState());
    }

    @Override
    public void v_RequestCreated() {
        System.out.print("-> (v_RequestCreated) ");
        assertEquals(State.REQUEST_CREATED, service.getState());
    }

    @Override
    public void v_Approved() {
        System.out.print("-> (v_Approved) ");
        assertEquals(State.APPROVED, service.getState());
    }

    @Override
    public void v_Rejected() {
        System.out.print("-> (v_Rejected) ");
        assertEquals(State.REJECTED, service.getState());
    }

    @Override
    public void v_OrderedFromSupplier() {
        System.out.print("-> (v_OrderedFromSupplier) ");
        assertEquals(State.ORDERED_SUPPLIER, service.getState());
    }

    @Override
    public void v_DeliveredToStore() {
        System.out.print("-> (v_DeliveredToStore) ");
        assertEquals(State.DELIVERED_STORE, service.getState());
    }

    @Override
    public void v_PickedUpByCustomer() {
        System.out.print("-> (v_PickedUpByCustomer) ");
        assertEquals(State.PICKED_UP, service.getState());
    }

    @Override
    public void e_CreateRequest() {
        System.out.print("-- e_CreateRequest -");
        service.createRequest();
    }

    @Override
    public void e_ApproveRequest() {
        System.out.print("-- e_ApproveRequest -");
        service.approveRequest();
    }

    @Override
    public void e_RejectRequest() {
        System.out.print("-- e_RejectRequest -");
        service.rejectRequest();
    }

    @Override
    public void e_OrderSupplier() {
        System.out.print("-- e_OrderSupplier -");
        service.orderFromSupplier();
    }

    @Override
    public void e_ReceiveFromSupplier() {
        System.out.print("-- e_ReceiveFromSupplier -");
        service.receiveFromSupplier();
    }

    @Override
    public void e_Pickup() {
        System.out.print("-- e_Pickup -");
        service.pickup();
    }

    @Override
    public void e_Reset() {
        System.out.println("-- e_Reset -");
        service.reset();
    }

    @Override
    public void e_ResetRejected() {
        System.out.println("-- e_ResetRejected -");
        service.reset();
    }
}
