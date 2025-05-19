package com.alibou.ecommerce.customer;

import org.springframework.stereotype.Component;



/*ù
* è un componente Spring (annotato con @Component) che svolge la
* funzione di mapper, cioè si occupa di convertire oggetti da
* un tipo a un altro, in questo caso:
*
*
*
* Metodo toCustomer(CustomerRequest request)
* Converte un oggetto CustomerRequest (probabilmente ricevuto dal client via API REST)
* in un oggetto Customer (entità interna usata dall'app, ad esempio per salvarlo nel database).
* Utilizza il builder pattern per costruire l'oggetto Customer.

* Controlla che request non sia null.

*
Metodo fromCustomer(Customer customer)
Converte un oggetto Customer (entità del dominio) in un oggetto CustomerResponse (probabilmente da restituire al client via API).

Costruisce un nuovo CustomerResponse con i dati dell'entità.

Anche qui controlla che customer non sia null.
* */
@Component
public class CustomerMapper {

  public Customer toCustomer(CustomerRequest request) {
    if (request == null) {
      return null;
    }
    return Customer.builder()
        .id(request.id())
        .firstname(request.firstname())
        .lastname(request.lastname())
        .email(request.email())
        .address(request.address())
        .build();
  }

  public CustomerResponse fromCustomer(Customer customer) {
    if (customer == null) {
      return null;
    }
    return new CustomerResponse(
        customer.getId(),
        customer.getFirstname(),
        customer.getLastname(),
        customer.getEmail(),
        customer.getAddress()
    );
  }
}
