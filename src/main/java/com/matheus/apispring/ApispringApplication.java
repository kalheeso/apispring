package com.matheus.apispring;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.matheus.apispring.domain.*;
import com.matheus.apispring.domain.enums.ClientType;
import com.matheus.apispring.domain.enums.PaymentStatus;
import com.matheus.apispring.repositories.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApispringApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private StateRepository stateRepo;

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private AdressRepository adressRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    public static void main(String[] args) {
        SpringApplication.run(ApispringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");
        Category cat3 = new Category(null, "Cama mesa banho");
        Category cat4 = new Category(null, "sexo");
        Category cat5 = new Category(null, "videogame");
        Category cat6 = new Category(null, "perfume");
        Category cat7 = new Category(null, "brinquedos");

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p1));

        p1.getCategories().addAll(Arrays.asList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().addAll(Arrays.asList(cat1));

        categoryRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepo.saveAll(Arrays.asList(p1, p2, p3));

        State s1 = new State(null, "Minas Gerais");
        State s2 = new State(null, "São Paulo");
        City c1 = new City(null, "Uberlândia", s1);
        City c2 = new City(null, "São Paulo", s2);
        City c3 = new City(null, "Campinas", s2);

        s1.getCities().addAll(Arrays.asList(c1));
        s2.getCities().addAll(Arrays.asList(c2, c3));

        stateRepo.saveAll(Arrays.asList(s1, s2));
        cityRepo.saveAll(Arrays.asList(c1, c2, c3));

        Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "123", ClientType.NATURALPERSON);

        cli1.getPhoneNumbers().addAll(Arrays.asList("8179", "8038"));

        Adress a1 = new Adress(null, "Rua flores", "300", "Apto 300", "Jardins", "3822", cli1, c1);
        Adress a2 = new Adress(null, "Av matos", "200", "Apto 10", "Lapa", "2143", cli1, c2);

        cli1.getAdresses().addAll(Arrays.asList(a1, a2));

        clientRepo.saveAll(Arrays.asList(cli1));
        adressRepo.saveAll(Arrays.asList(a1, a2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Order o1 = new Order(null, sdf.parse("30/09/2012 10:23"), cli1, a1);
        Order o2 = new Order(null, sdf.parse("10/09/2012 08:21"), cli1, a2);

        Payment pay1 = new CreditCardPayment(null, PaymentStatus.COMPLETE, o1, 6);

        o1.setPayment(pay1);

        Payment pay2 = new BankSlipPayment(null, PaymentStatus.PENDING, o2, sdf.parse("20/10/2017 00:00"), null);
        o2.setPayment(pay2);

        cli1.getOrders().addAll(Arrays.asList(o1, o2));

        orderRepo.saveAll(Arrays.asList(o1, o2));
        paymentRepo.saveAll(Arrays.asList(pay1, pay2));

        OrderItem oi1 = new OrderItem(o1, p1, 0.0, 1, 2000.0);
        OrderItem oi2 = new OrderItem(o1, p3, 0.0, 2, 80.0);
        OrderItem oi3 = new OrderItem(o2, p2, 10.0, 1, 800.0);

        o1.getItens().addAll(Arrays.asList(oi1, oi2));
        o2.getItens().addAll(Arrays.asList(oi3));

        p1.getItens().addAll(Arrays.asList(oi1));
        p2.getItens().addAll(Arrays.asList(oi3));
        p3.getItens().addAll(Arrays.asList(oi2));

        orderItemRepo.saveAll(Arrays.asList(oi1, oi2, oi3));
    }

}