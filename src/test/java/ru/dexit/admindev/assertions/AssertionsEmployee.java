package ru.dexit.admindev.assertions;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import ru.dexit.admindev.data.Role;
import ru.dexit.admindev.models.employee.AddEmployeeRequestModel;
import ru.dexit.admindev.models.employee.EmployeeCommonResponseModel;
import ru.dexit.admindev.models.employee.ODataEmployeeResponseModel;
import ru.dexit.admindev.models.employee.UpdateEmployeeRequestModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionsEmployee {

    public static void employeeAddedSuccessfully(Response response, AddEmployeeRequestModel requestBody){

        EmployeeCommonResponseModel responseBody = response.body().as(EmployeeCommonResponseModel.class);

        // Check status code

        assertEquals(HttpStatus.SC_OK, response.statusCode(), "Incorrect status code");

        // Check response body

        assertFalse(responseBody.id.isEmpty(), "Employee ID is empty!");
        assertEquals(requestBody.name, responseBody.name, "Employee name in response is equal to name in request");
        assertFalse(responseBody.createdUtc.isEmpty(), "createdUtc is empty");
        assertNull(responseBody.deletedUtc, "deletedUtc is not null");
        assertEquals(requestBody.roleId, responseBody.roleId, "roleId in response is not equal to roleId in request");
        assertEquals(Role.findRoleById(requestBody.roleId).roleName, responseBody.role, "role is not equal to role in request body");
        assertEquals(requestBody.email.toLowerCase(), responseBody.email, "email in response is not equal to email in request");
        assertNull(responseBody.activationDate, "activationDate is not equal to null");

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());
    }

    public static void employeeUpdatedSuccessfully(Response response, UpdateEmployeeRequestModel requestBody, EmployeeCommonResponseModel responseBodyCreation){

        EmployeeCommonResponseModel responseBody = response.body().as(EmployeeCommonResponseModel.class);

        // Check status code

        assertEquals(HttpStatus.SC_OK, response.statusCode(), "Incorrect status code");

        // Check response body

        assertEquals(requestBody.id, responseBody.id, "Employee ID in response is not equal to Employee ID in request");
        assertEquals(requestBody.name, responseBody.name, "Employee name in response is equal to name in request");
        assertFalse(responseBody.createdUtc.isEmpty(), "createdUtc is empty");
        assertNull(responseBody.deletedUtc, "deletedUtc is not null");
        assertEquals(requestBody.roleId, responseBody.roleId, "roleId in response is not equal to roleId in request");
        assertEquals(Role.findRoleById(requestBody.roleId).roleName, responseBody.role, "role is not equal to role in request body");
        assertEquals(responseBodyCreation.email, responseBody.email, "email in response is not equal to Employee email");
        assertNull(responseBody.activationDate, "activationDate is not equal to null");

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());
    }

    public static void invitationUpdatedSuccessfully(Response response, EmployeeCommonResponseModel responseBodyCreation){
        EmployeeCommonResponseModel responseBody = response.body().as(EmployeeCommonResponseModel.class);

        // Check status code

        assertEquals(HttpStatus.SC_OK, response.statusCode(), "Incorrect status code");

        // Check response body

        assertEquals(responseBodyCreation.id, responseBody.id, "Employee ID in response is not equal to Employee ID in request");
        assertEquals(responseBodyCreation.name, responseBody.name, "Employee name in response is equal to name in request");
        assertFalse(responseBody.createdUtc.isEmpty(), "createdUtc is empty");
        assertNull(responseBody.deletedUtc, "deletedUtc is not null");
        assertEquals(responseBodyCreation.roleId, responseBody.roleId, "roleId in response is not equal to roleId in request");
        assertEquals(Role.findRoleById(responseBodyCreation.roleId).roleName, responseBody.role, "role is not equal to Test FullWriteRole");
        assertEquals(responseBodyCreation.email, responseBody.email, "email in response is not equal to Employee email");
        assertNull(responseBody.activationDate, "activationDate is not equal to null");

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());
    }


    public static void oDataEmployeeReturnsData(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_OK, response.statusCode(), "Incorrect status code");

        // Check response body

        List<ODataEmployeeResponseModel> responseData = response.jsonPath().get("value");

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());
    }

    public static void employeeDeletedSuccessfully(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_OK, response.statusCode(), "Incorrect status code");

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }

    public static void employeeIsNotCreatedInvalidName(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }

    public static void employeeIsNotCreatedInvalidRole(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }

    public static void employeeIsNotCreatedWithRoleFullRights(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_PRECONDITION_FAILED, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }
    public static void employeeIsNotCreatedWithNotExistRole(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }

    public static void employeeIsNotUpdatedDuplicateEmail(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_CONFLICT, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }

    public static void employeeIsNotUpdatedInvalidEmail(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }

    public static void employeeIsNotUpdatedInvalidID(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }

    public static void employeeIsNotUpdatedNotExistID(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }

    public static void employeeIsNotUpdatedAdministratorIsReadOnly(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_PRECONDITION_FAILED, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }

    public static void employeeIsNotUpdatedInvalidName(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }
    public static void employeeIsNotUpdatedInvalidRoleID(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }

    public static void employeeIsNotUpdatedAssigningFullRights(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_PRECONDITION_FAILED, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }

    public static void employeeIsNotUpdatedRoleNotExist(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }

    public static void invitationIsNotSentInvalidID(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }

    public static void invitationIsNotSentNotExistID(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }

    public static void employeeIsNotDeletedInvalidID(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }
    public static void employeeIsNotDeletedNotExistID(Response response){

        // Check status code

        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode(), "Incorrect status code");

        // TODO: Добавить проверку моделей

        // Check response time

        assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < 500, "Response time more than 500 ms, actual is " + response.time());

    }

    // TODO: подумать о том, чтобы вынести проверку времени ответа в отдельный класс AssertionsCommon или AssertionsBase и сделать ассерт в AfterEach

}
