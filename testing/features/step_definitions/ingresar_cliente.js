const assert = require('assert');
const { Given, When, Then } = require('cucumber');
const request = require('sync-request');

Given('que se ingresa el cliente con {string} y {int}', function (razonSocial, cuit) {
    this.razonSocial = razonSocial;
    this.cuit = cuit;
});

When('guardo un cliente', function () {
    const newCliente = {
        razonSocial: this.razonSocial,
        cuit: this.cuit
    };

    const postResponse = request('POST', 'http://backend:8080/clientes', {
        json: newCliente
    });

    this.response = {
        status: postResponse.statusCode,
        message: postResponse.getBody('utf8')
    };
});

Then('se espera el siguientes {int} con la {string}', function (expectedStatus, expectedMessage) {
    assert.strictEqual(this.response.status, expectedStatus);

    const parsedResponse = JSON.parse(this.response.message);
    const actualMessage =  `Cliente ${parsedResponse.data.razonSocial} (${parsedResponse.data.cuit}) registrado correctamente`;
    assert.strictEqual(actualMessage, expectedMessage);

    /*const deleteResponse = request('DELETE', 'http://backend:8080/clientes', {
        json: newCliente
    });*/
});