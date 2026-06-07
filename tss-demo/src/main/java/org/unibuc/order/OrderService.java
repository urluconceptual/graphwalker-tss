package org.unibuc.order;

public class OrderService {

    private State state = State.NONE;

    public State getState() {
        return state;
    }

    public void createRequest() {
        if (state != State.NONE) {
            throw new IllegalStateException("Request can be created only from NONE");
        }
        state = State.REQUEST_CREATED;
    }

    public void approveRequest() {
        if (state != State.REQUEST_CREATED) {
            throw new IllegalStateException("Approve allowed only from REQUEST_CREATED");
        }
        state = State.APPROVED;
    }

    public void rejectRequest() {
        if (state != State.REQUEST_CREATED) {
            throw new IllegalStateException("Reject allowed only from REQUEST_CREATED");
        }
        state = State.REJECTED;
    }

    public void orderFromSupplier() {
        if (state != State.APPROVED) {
            throw new IllegalStateException("Order allowed only from APPROVED");
        }
        state = State.ORDERED_SUPPLIER;
    }

    public void receiveFromSupplier() {
        if (state != State.ORDERED_SUPPLIER) {
            throw new IllegalStateException("Receive allowed only from ORDERED_SUPPLIER");
        }
        state = State.DELIVERED_STORE;
    }

    public void pickup() {
        if (state != State.DELIVERED_STORE) {
            throw new IllegalStateException("Pickup allowed only after DELIVERED_STORE");
        }
        state = State.PICKED_UP;
    }

    public void reset() {
        state = State.NONE;
    }
}
