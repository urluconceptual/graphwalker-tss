package org.unibuc;

import org.graphwalker.java.annotation.Edge;
import org.graphwalker.java.annotation.Vertex;

public interface OrderModel {

    @Vertex()
    void v_Start();

    @Vertex()
    void v_RequestCreated();

    @Vertex()
    void v_Approved();

    @Vertex()
    void v_Rejected();

    @Vertex()
    void v_OrderedFromSupplier();

    @Vertex()
    void v_DeliveredToStore();

    @Vertex()
    void v_PickedUpByCustomer();

    @Edge()
    void e_CreateRequest();

    @Edge()
    void e_ApproveRequest();

    @Edge()
    void e_RejectRequest();

    @Edge()
    void e_OrderSupplier();

    @Edge()
    void e_ReceiveFromSupplier();

    @Edge()
    void e_Pickup();

    @Edge()
    void e_Reset();
}
