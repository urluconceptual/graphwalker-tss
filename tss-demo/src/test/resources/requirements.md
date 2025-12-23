# State requirements

## v_Start

Requirement key: REQ_INIT  
Description: The system is in the initial state with no special order request created or active.  
Business goal: Provide a clear starting point for all test flows and order scenarios.

## v_RequestCreated

Requirement key: REQ_REQUEST_STORED  
Description: When the user creates a request, it is stored in the system, assigned an identifier, and becomes visible in
the list of pending requests.  
Business goal: Ensure that no user‑initiated request is lost and that each request can later be approved or rejected.

## v_Approved

Requirement key: REQ_APPROVED_STATE  
Description: A request in this state has been reviewed and approved by an authorized user, is clearly marked as
“Approved” in the UI, and is available for ordering from the supplier.  
Business goal: Clearly distinguish approved requests from pending or rejected ones to avoid incorrect processing.

## v_Rejected

Requirement key: REQ_REJECTED_STATE  
Description: The request has been rejected by an authorized user, the rejection reason is stored, and the request cannot
proceed to ordering and delivery steps.  
Business goal: Prevent processing of invalid requests and provide traceability for rejection decisions.

## v_OrderedFromSupplier

Requirement key: REQ_SUPPLIER_ORDERED_STATE  
Description: For an approved request, the system can generate and record a purchase order to the supplier, and this
state indicates that the product is in the process of being supplied.  
Business goal: Provide visibility into orders that have been sent to suppliers and are currently in transit.

## v_DeliveredToStore

Requirement key: REQ_DELIVERED_STATE  
Description: The ordered product has been received in the store, the quantity and delivery details are recorded, and the
product is ready for customer pickup.  
Business goal: Clearly indicate which orders are available for pickup and confirm that supplier delivery has been
completed.

## v_PickedUpByCustomer

Requirement key: REQ_COMPLETED_FLOW  
Description: The customer has picked up the product from the store, and the system marks the request/order as completed,
updating any related payment or stock information.  
Business goal: Fully close the “request → approval → order → delivery → pickup” cycle and maintain an accurate history
of completed orders.
