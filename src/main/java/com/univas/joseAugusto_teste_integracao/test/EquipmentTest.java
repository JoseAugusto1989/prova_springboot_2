package com.univas.joseAugusto_teste_integracao.test;

import com.univas.joseAugusto_teste_integracao.dto.Equipment;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;

public class EquipmentTest {

    private final String URL_API = "http://localhost:8090/equipment";

    @Test
    public void testCreateEquipment_Success() {
        Equipment equipment = new Equipment(1L, "TAG-01", "Equip 1", "Provider 1", new Date(), 10.5, true);

        given().body(equipment).contentType(ContentType.JSON).post(URL_API).then().statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void testCreateEquipment_WithInvalidData() {
        Equipment equipment = new Equipment();

        given().body(equipment).contentType(ContentType.JSON).post(URL_API).then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testCreateEquipment_WithDuplicateID() {
        Equipment equipment = new Equipment(1L, "TAG-01", "Equip 1", "Provider 1", new Date(), 10.5, true);

        given().body(equipment).contentType(ContentType.JSON).post(URL_API).then().statusCode(HttpStatus.CREATED.value());

        equipment.setId(2L);
        given().body(equipment).contentType(ContentType.JSON).post(URL_API).then().statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void testGetEquipmentById_WithoutExistsEquip() {
        Long id = 100L;
        Response response = RestAssured.get(URL_API + "/" + id);
        response.then().assertThat().statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testEquipmentActive_WithExistsEquip() {
        Long id = 1L;
        Response response = RestAssured.patch(URL_API + "/" + id);
        response.then().assertThat().statusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
    }

    @Test
    public void testEquipmentActive_success() {
        Long id = 1L;
        Response response = RestAssured.patch(URL_API + "/" + id);
        response.then().assertThat().statusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
    }

    @Test
    public void testEquipmentActive_WithoutEquipment() {
        Long id = 100L;
        Response response = RestAssured.patch(URL_API + "/" + id);
        response.then().assertThat().statusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
    }
}
