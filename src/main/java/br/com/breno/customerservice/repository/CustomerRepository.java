package br.com.breno.customerservice.repository;

import br.com.breno.customerservice.model.Customer;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Optional;

@Repository
public class CustomerRepository {

    private final DynamoDbTable<Customer> customerTable;

    public CustomerRepository(DynamoDbEnhancedClient enhancedClient) {
        this.customerTable = enhancedClient.table("Customer", TableSchema.fromBean(Customer.class));
    }

    public void save(Customer customer) {
        customerTable.putItem(customer);
    }

    public Optional<Customer> findById(String customerId) {
        return Optional.ofNullable(customerTable.getItem(Key.builder().partitionValue(customerId).build()));
    }

    public void deleteById(String customerId) {
        customerTable.deleteItem(Key.builder().partitionValue(customerId).build());
    }

    public Iterable<Customer> findAll() {
        return customerTable.scan().items();
    }
}
