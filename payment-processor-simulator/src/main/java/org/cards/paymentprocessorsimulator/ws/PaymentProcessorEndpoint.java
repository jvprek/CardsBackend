package org.cards.paymentprocessorsimulator.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.cards.services.payment_processor.*;


@Endpoint
public class PaymentProcessorEndpoint {
	private static final String NAMESPACE_URI = "http://cards.org/services/payment-processor";

	private PaymentProcessorRepository paymentProcessorRepository;

	@Autowired
	public PaymentProcessorEndpoint(PaymentProcessorRepository paymentProcessorRepository) {
		this.paymentProcessorRepository = paymentProcessorRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPendingTransactionsRequest")
	@ResponsePayload
	public GetPendingTransactionsResponse getCountry(@RequestPayload GetPendingTransactionsRequest request) {
		GetPendingTransactionsResponse response = new GetPendingTransactionsResponse();
		response.setCountry(paymentProcessorRepository.findCountry(request.getName()));

		return response;
	}
}
