# CARDS Domain Microservices and Utilities
## Overview
In a fictional Bank, the Cards Domain division, designs, implements and supports software, that provides to the customers of the Bank credit card related services.
There are two categories of services.

1. Information-related requests from a client, such as an Account Information, Transaction History, etc.
2. User requests such as reporting a card as 'Lost or Stollen', requesting a replacement for a damaged Card, etc.

The above services are consumed from various channels of the Bank such as 'Web Banking', 'Mobile Phone Banking', and 'Retail Banking'.
The Cards Domain does not 'own' most of these data, and the required data are extracted and consumed from services of other domains.

In the scope of this, we consider two collaborating domains:
1. Core Domain: Covers most of the traditional banking IT operations. From the perspective of the Cards Domain, there are two categories of services that are relevant, payments and account information. Since Core Domain owns Retail Banking, consumes Card Domain services  related to user requests.
2. Payment Processor: Third-party entity that provides the infrastructure to allow Bank Credit Card customers to perform transactions with the various merchants. The infrastructure is tightly coupled to Core Banking.

![Domain Interconnect](images/DomainInterconnect.png)
