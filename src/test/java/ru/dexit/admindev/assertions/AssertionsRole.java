package ru.dexit.admindev.assertions;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import ru.dexit.admindev.models.Role.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionsRole {
    public static void roleCreatedSuccessfully(Response response, AddRoleRequestModel requestBody){

        RoleCommonResponseModel responseBody = response.as(RoleCommonResponseModel.class);

        // Check status code

        assertEquals(HttpStatus.SC_OK, response.statusCode(), "Incorrect status code");

        // Check response body

        assertFalse(responseBody.id.isEmpty(), "Role id is empty");
        assertEquals(requestBody.name, responseBody.name, "Role name in response is equal to role name in request");
        assertEquals(requestBody.policies, responseBody.policies, "Role policies in response is not equal to role policies in request");
        assertFalse(responseBody.createdUtc.isEmpty(), "createdUtc is empty");
        assertNull(responseBody.deletedUtc, "deletedUtc is not null");

        // Check response time
        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms");

    }

    public static void roleUpdatedSuccessfully(Response response, UpdateRoleRequestModel requestBody){

        RoleCommonResponseModel responseBody = response.as(RoleCommonResponseModel.class);

        Collections.sort(requestBody.policies);
        Collections.sort(responseBody.policies);

        // Check status code

        assertEquals(HttpStatus.SC_OK, response.statusCode(), "Incorrect status code");

        // Check response body

        assertEquals(requestBody.id, responseBody.id, "Role ID in response is not equal to Role ID in request");
        assertEquals(requestBody.name, responseBody.name, "Role name in response is not equal to role name in request");
        assertEquals(requestBody.policies, responseBody.policies, "Role policies in response is not equal to role policies in request");
        assertFalse(responseBody.createdUtc.isEmpty(), "createdUtc is empty");
        assertNull(responseBody.deletedUtc, "deletedUtc is not null");

        // Check response time
        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms");

    }

    public static void roleDeletedSuccessfully(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_OK, response.statusCode(), "Incorrect status code");

        // Check response time
        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms");

    }

    public static void policiesGotSuccessfully(Response response) {

        // Check status code

        assertEquals(HttpStatus.SC_OK, response.statusCode(), "Incorrect status code");

        // Check response body

        List<GetPoliciesResponseModel> responseBody = response.jsonPath().get();

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms");

    }

    public static void oDataRoleReturnsData(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_OK, response.statusCode(), "Incorrect status code");

        // Check response body

        List<ODataRoleResponseModel> responseBody = response.jsonPath().get("value");

        // Check response time
        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms");


    }
}