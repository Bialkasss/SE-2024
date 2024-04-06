# Auction system

## Introduction

Specification of functional requirements as part of computerisation of the product sale process based on the auction mechanism.

## Business processes

---
<a id="bc1"></a>
### BC1: Auction sale

**Actors:** [Seller](#ac1), [Buyer](#ac2)

**Description:** Business process describing a sale by the auction mechanism.

**Main scenario:**
1. [Seller](#ac1) offers the product at an auction. ([UC1](#uc1))
2. [Buyer](#ac2) offers a bid for the product that is higher than the currently highest bid. ([BR1](#br1))
3. [Buyer](#ac2) wins the auction ([BR2](#br2))
4. [Buyer](#ac2) transfers the amount due to the Seller([UC3](#uc3)).
5. [Seller](#ac1) transfers the product to the Buyer([UC4](#uc4)).

**Alternative scenarios:** 

2.A. Buyer's bid has been outbid and [Buyer](#ac2) wants to outbid the current highest bid.
* 2.A.1. Continue at step 2.

3.A. Auction time has elapsed and [Buyer](#ac2) has lost the auction. ([BR2](#br2))
* 3.A.1. End of use case.

---

## Actors

<a id="ac1"></a>
### AC1: Seller

A person offering goods at an auction.

<a id="ac2"></a>
### AC2: Buyer

A person intending to purchase a product at an auction..


## User level use cases

### Actors and their goals 

[Seller](#ac1):
* [UC1](#uc1): Offering a product at an auction
* [UC4](#uc4): Transfering the product to the [Buyer](#ac2)

[Buyer](#ac2):
* [UC2](#uc2): Offering a bid for the prouct
* [UC3](#uc3): Transfering the amount due to the [Seller](#ac1)
* Winning the auction ([BR2](#br2))

---
<a id="uc1"></a>
### UC1: Offering a product at an auction

**Actors:** [Seller](#ac1)

**Main scenario:**
1. [Seller](#ac1) reports to the system the willingness to offer the product up at an auction.
2. System asks for the product data and initial price.
3. [Seller](#ac1) provides product data and the initial price.
4. System verifies data correctness.
5. System informs that the product has been successfully put up for auction.

**Alternative scenarios:** 

4.A. Incorrect or incomplete product data has been entered.
* 4.A.1. informs about incorrectly entered data.
* 4.A.2. Continue at step 2.

---

<a id="uc2"></a>
### UC2: Offering a bid for the prouct

**Actors:** [Seller](#ac1), [Buyer](#ac2)

**Main scenario:**
1. The [Buyer](#ac2) chooses a product to place a bid.
2. System asks the [Buyer](#ac2) for the amount they want to bid.
3. The [Buyer](#ac2) enters amount for their bid.
4. System verifies the amount placed.
5. System updates the status of bidding and notifies the [Seller](#ac1) about it. 

**Alternative scenarios:** 

4.A. Too low amount entered for the bid.
* 4.A.1. System shows notification to a [Buyer](#ac2) about incorrect amount. 
* 4.A.2. Continue at step 3.

---

<a id="uc3"></a>
### UC3: Transfering the amount due to the [Seller](#ac1)

**Actors:** [Seller](#ac1), [Buyer](#ac2)

**Main scenario:**
1. The System notifies the [Buyer](#ac2) that they have to transfer the money.
2. The [Buyer](#ac2) notifies system that they are ready to transfer.
3. The System asks The [Buyer](#ac2) for payment information.
4. The [Buyer](#ac2) provides payment information.
5. The system checks for correctness of payment method.
6. The system notifies the [Seller](#ac1) about new transfer of money.
7. The system notifies the [Buyer](#ac2) that the payment was successfull.

**Alternative scenarios:** 

5.A. Payment method was incorrect.
* 5.A.1. System shows notification to a [Buyer](#ac2) about incorrect payment method. 
* 5.A.2. Continue at step 3.

---

<a id="uc4"></a>
### UC4: Transfering the product to the [Buyer](#ac2).

**Actors:** [Seller](#ac1), [Buyer](#ac2)

**Main scenario:**
1. The System asks the [Buyer](#ac2) about shipment information.
2. The [Buyer](#ac2) provides shipment information.
3. The [Seller](#ac1) recieves shipment information of a [Buyer](#ac2) from the system.
4. The [Seller](#ac1) ships the product to the [Buyer](#ac2).
5. The System notifies the [Buyer](#ac2) about shipment.
6. The [Buyer](#ac2) notifies the system that the shipment was recieved.
7. The system notifies the [Seller](#ac1) about the successfull shipment. 
8. The system moves the auction process to the archives.

**Alternative scenarios:** 

2.A. The [Buyer](#ac2) did not provide shipment information
* 2.A.1. Go to step 1.

4.A. The [Seller](#ac1) does not ship the product.
* 4.A.1. The system notifies the [Buyer](#ac2) that the shipment will not be made.
* 4.A.2 The system transfers back the money from the [Seller](#ac1) to the [Buyer](#ac2).
* 4.A.3. The system notifies the [Seller](#ac1) about the transfer back of money.
* 4.A.4. The system notifies the [Buyer](#ac2) about the recieved transfer.
* 4.A.3. End of use case.

---
## Business objects (also known as domain or IT objects)

### BO1: Auction

The auction is a form of concluding a sale and purchase transaction in which the Seller specifies the starting bid of the product, while the Buyers may offer their own purchase offer, each time proposing a bid higher than the currently offered bid. The auction ends after a specified period of time. If at least one purchase offer has been submitted, the product is purchased by the Buyer who offered the highest bid. 

### BO2: Product

A physical or digital item to be auctioned.

### BO3: Shipment

Process of transporting the product via external company chosen by the [Seller](#ac1)

## Business rules

<a id="br1"></a>
### BR1: Bidding at auction

Bidding at auction requires submitting an amount higher than current by a minimum of EUR 1.00

<a id="br2"></a>
### BR2: Winning an auction

Auction is won by [Buyer](#ac2) who submitted the highest bid before the end of the auction (time expires).


## CRUDL Matrix


| Use case                                      | Auction | Product | Shipment |
| --------------------------------------------- | ------- | ------- | -------- |
| UC1: Offering a product at an auction         |    C    |    C    |     -    |
| UC2: Offering a bid for the product           |    R    |    -    |     -    |
| UC3: Transfering the amount due to the Seller |    R    |    -    |     -    |
| UC4: Transfering the product to the Buyer     |    R    |    U    |     C    |
