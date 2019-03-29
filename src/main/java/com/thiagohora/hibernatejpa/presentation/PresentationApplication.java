package com.thiagohora.hibernatejpa.presentation;

import com.thiagohora.hibernatejpa.presentation.entity.Address;
import com.thiagohora.hibernatejpa.presentation.entity.AddressRepo;
import com.thiagohora.hibernatejpa.presentation.entity.Author;
import com.thiagohora.hibernatejpa.presentation.entity.AuthorRepo;
import com.thiagohora.hibernatejpa.presentation.entity.Book;
import com.thiagohora.hibernatejpa.presentation.entity.BookRepo;
import com.thiagohora.hibernatejpa.presentation.entity.City;
import com.thiagohora.hibernatejpa.presentation.entity.CityRepo;
import com.thiagohora.hibernatejpa.presentation.entity.Country;
import com.thiagohora.hibernatejpa.presentation.entity.CountryRepo;
import com.thiagohora.hibernatejpa.presentation.entity.Publisher;
import com.thiagohora.hibernatejpa.presentation.entity.PublisherRepo;
import com.thiagohora.hibernatejpa.presentation.entity.TestService;
import com.thiagohora.hibernatejpa.presentation.infrastructure.persistence.JpaSpecificationExecutorImpl;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.transaction.Transactional;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = JpaSpecificationExecutorImpl.class)
public class PresentationApplication implements ApplicationContextAware {

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(PresentationApplication.class, args);

		final TestService service = context.getBean(TestService.class);
		service.doSomething();
	}

	@Override @Transactional
	public void setApplicationContext(ApplicationContext context) throws BeansException {

		final PublisherRepo publisherRepo = context.getBean(PublisherRepo.class);
		final AuthorRepo authorRepo = context.getBean(AuthorRepo.class);
		final BookRepo bookRepo = context.getBean(BookRepo.class);
		final AddressRepo addressRepo = context.getBean(AddressRepo.class);
		final CityRepo cityRepo = context.getBean(CityRepo.class);
		final CountryRepo countryRepo = context.getBean(CountryRepo.class);

		final Author author1 = new Author("Author 1", "Test");
		final Author author2 = new Author("Author 2", "Test");
		final Author author3 = new Author("Author Test", "Insert 1");
		final Author author4 = new Author("Author Test", "Insert 2");
		final Author author5 = new Author("Author Test", "Insert 3");
		final Author author6 = new Author("Author Test", "Insert 4");

		final Country country = new Country("España", "ES");
		countryRepo.save(country);

		final City city = new City("Barcelona", "BCN", country);
		cityRepo.save(city);

		final Address address = new Address("Test", "10", "ap 11", "08024", city);
		addressRepo.save(address);

		author1.setAddress(address);

		authorRepo.save(author1);
		authorRepo.save(author2);

		final Publisher publisher1 = new Publisher("Publisher 1", "Test");
		final Publisher publisher2 = new Publisher("Publisher 2", "Test");

		publisherRepo.save(publisher1);
		publisherRepo.save(publisher2);

		final Book book1 = new Book("Test 1", publisher1, author1);
		final Book book2 = new Book("Test 2", publisher2, author2);

		bookRepo.save(book1);
		bookRepo.save(book2);

		final Country country1 = new Country("Brazil", "BR");
		countryRepo.save(country1);

		final City city1 = new City("Santos", "SAN", country);
		cityRepo.save(city1);

		final Address address1 = new Address("Test", "10", "ap 11", "11025010", city);

		addressRepo.save(address1);

		authorRepo.save(Arrays.asList(author3, author4, author5, author6));

		System.out.println("-------------------------------------------------------------------");
	}
}
