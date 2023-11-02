package com.apress.book.ldap.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@DisplayName("Hello world container tests cases")
class HelloWorldContainerTest {

    @Container
    public static GenericContainer container = new GenericContainer(DockerImageName.parse("testcontainers/helloworld"));

    @Test
    @DisplayName("Check that the test and the container works fine")
    void should_works_the_container() {

    }
}
