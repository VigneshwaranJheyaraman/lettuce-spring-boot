# EFX POC
## Data flow
1. You call the endpoint `http://localhost:8080/api/efx/inquiry-party` **[POST]** method, with PartyInqReqType as JSON body
2. Now internally, `/api/efx/inquiry-party` will call **EFX API's endpoint**, which is going to return us the entire account and other information
3. You get the response from _EFX's API_ and then parse it into **PartyInqResType**.
4. Return the `PartyInqResType` to UI/ Frontend, which they will use

## How we have implemented
* We have two endpoints
  * `/api/efx/inquiry-party`
    * This is the endpoint, which UI/Front end will call to get account info
    * This endpoint, will call EFX API and based on the response from EFX's API will send it to UI/Front-end.
    * When user calls this endpoint with party related info, this will call EFX's API with the same info provided and based on the response from API, the response changes here
  * `/api/efx/efx-internal` 
    * For POC purpose, Let us consider this endpoint to the **EFX API**, which will returning us the account details as JSON response.
    * This will generate a response if valid account details were given.
    * For POC purpose, we have a `SamplePartyResponseType`, which is **MOCK** structur and not exact structure.

## DIY
* Start the spring boot application
* Open Browser / POSTMAN
  * If Browser, run this is Developer Console
  ```js
  fetch("http://localhost:8080/api/efx/inquiry-party" ,{method:"POST", body : JSON.stringify({sel: {partyKeys: {partyId: "PA-1", partyIdent: "PA_12345"}}}), headers: {"Content-Type" :"application/json"}})
  ```
  * If POSTMAN, specify the URL and inside body have this JSON
  ```json
  {"sel": {"partyKeys": {"partyId": "PA-1", "partyIdent": "PA_12345"}}}
  ```
* You will get a response like
```json
{
    "javaObjectName": "PartyInquiryRequest<PartySelection<PartyKey<PA-1,PA_12345>>>",
    "requestSent": {
        "sel": {
            "partyKeys": {
                "partyId": "PA-1",
                "partyIdent": "PA_12345"
            }
        }
    }
}
```