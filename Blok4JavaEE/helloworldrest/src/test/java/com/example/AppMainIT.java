package com.example;

import com.example.model.Book;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.net.URL;

import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Arquillian.class)
public class AppMainIT {

    @ArquillianResource
    private URL deploymentURL;

    private String booksResourcePath;

    @Deployment // 2: creeer een war zodat arq deze kan deployen
    public static Archive<?> createDeployment() {
        WebArchive warEmpty = ShrinkWrap.create(WebArchive.class, "AppMainIT.war");
        WebArchive warFilled = warEmpty
                .addPackages(true, AppMain.class.getPackage()) // add all packages in my application
                // .addClass(App.class) // dont forget! // selectively add classes
                // .addClass(ContactsResource.class)
                // .addClass(Contact.class)
                .addAsWebInfResource("test-beans.xml", "beans.xml") // to activate CDI
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
        //.addAsLibraries(pomDependency("org.apache.logging.log4j", "log4j-slf4j-impl")); Voor logger, die gebruik ik niet in de app dus deze is voor nu niet nodig.
        System.out.println(warFilled.toString(true));
        return warFilled;
    }

    @Before
    public void setup() {
        booksResourcePath = deploymentURL + "api/books";
    }

    @Test // 3: maak testjes
    public void whenContactIsPostedICanGetIt() {
        Client httpClient = ClientBuilder.newClient();

        Book c = Book.builder().id(1).title("ACOTAR").author("Sarah J. Maas").build();

        String postedContact = httpClient
                .target(booksResourcePath) // URI
                .request()
                .post(entity(c, APPLICATION_JSON), String.class);

        assertThat(postedContact, containsString("Book ACOTAR is toegevoegd aan uw boekenkast"));
    }
}
