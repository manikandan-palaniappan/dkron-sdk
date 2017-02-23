/*
 * DkronRESTAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.api.dkron.controllers;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import com.fasterxml.jackson.core.type.TypeReference;

import com.api.dkron.*;
import com.api.dkron.models.*;
import com.api.dkron.exceptions.*;
import com.api.dkron.http.client.HttpClient;
import com.api.dkron.http.client.HttpContext;
import com.api.dkron.http.request.HttpRequest;
import com.api.dkron.http.response.HttpResponse;
import com.api.dkron.http.response.HttpStringResponse;
import com.api.dkron.http.client.APICallBack;
import com.api.dkron.controllers.syncwrapper.APICallBackCatcher;

public class MembersController extends BaseController {    
    //private static variables for the singleton pattern
    private static Object syncObject = new Object();
    private static MembersController instance = null;

    /**
     * Singleton pattern implementation 
     * @return The singleton instance of the MembersController class 
     */
    public static MembersController getInstance() {
        synchronized (syncObject) {
            if (null == instance) {
                instance = new MembersController();
            }
        }
        return instance;
    }

    /**
     * List members.
     * @return    Returns the List<Member> response from the API call 
     */
    public List<Member> getMember(String baseUri
    ) throws Throwable {
        APICallBackCatcher<List<Member>> callback = new APICallBackCatcher<List<Member>>();
        getMemberAsync(callback, baseUri);
        if(!callback.isSuccess())
            throw callback.getError();
        return callback.getResult();
    }

    /**
     * List members.
     * @return    Returns the void response from the API call 
     */
    public void getMemberAsync(
                final APICallBack<List<Member>> callBack,
                final String baseUri
    ) {
        
        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(baseUri);
        _queryBuilder.append("/members");
        //validate and preprocess url
        String _queryUrl = APIHelper.cleanUrl(_queryBuilder);

        //load all headers for the outgoing API request
        Map<String, String> _headers = new HashMap<String, String>() {
            private static final long serialVersionUID = 4784266398193220563L;
            {
                    put( "user-agent", "APIMATIC 2.0" );
                    put( "accept", "application/json" );
            }
        };

        //prepare and invoke the API call request to fetch the response
        final HttpRequest _request = getClientInstance().get(_queryUrl, _headers, null);

        //invoke the callback before request if its not null
        if (getHttpCallBack() != null)
        {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        //invoke request and get response
        Runnable _responseTask = new Runnable() {
            public void run() {
                //make the API call
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {

                            //invoke the callback after response if its not null
                            if (getHttpCallBack() != null)	
                            {
                                getHttpCallBack().OnAfterResponse(_context);
                            }

                            //handle errors defined at the API level
                            validateResponse(_response, _context);

                            //extract result from the http response
                            String _responseBody = ((HttpStringResponse)_response).getBody();
                            List<Member> _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<List<Member>>(){});

                            //let the caller know of the success
                            callBack.onSuccess(_context, _result);
                        } catch (APIException error) {
                            //let the caller know of the error
                            callBack.onFailure(_context, error);
                        } catch (IOException ioException) {
                            //let the caller know of the caught IO Exception
                            callBack.onFailure(_context, ioException);
                        } catch (Exception exception) {
                            //let the caller know of the caught Exception
                            callBack.onFailure(_context, exception);
                        }
                    }
                    public void onFailure(HttpContext _context, Throwable _error) {
                        //invoke the callback after response if its not null
                        if (getHttpCallBack() != null)	
                            {
                            getHttpCallBack().OnAfterResponse(_context);
                        }

                        //let the caller know of the failure
                        callBack.onFailure(_context, _error);
                    }
                });
            }
        };

        //execute async using thread pool
        APIHelper.getScheduler().execute(_responseTask);
    }

}