/*
 * DkronRESTAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.api.dkron.resources;

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

public class ExecutionResource extends BaseResource {    
    //private static variables for the singleton pattern
    private static Object syncObject = new Object();
    private static ExecutionResource instance = null;

    /**
     * Singleton pattern implementation 
     * @return The singleton instance of the ExecutionResource class 
     */
    public static ExecutionResource getInstance() {
        synchronized (syncObject) {
            if (null == instance) {
                instance = new ExecutionResource();
            }
        }
        return instance;
    }

    /**
     * List executions.
     * @param    jobName    Required parameter: The job that owns the executions to be fetched.
     * @return    Returns the List<Execution> response from the API call 
     */
    public List<Execution> listExecutionsByJob(
                final String jobName, final String baseUri) throws Throwable {
        APICallBackCatcher<List<Execution>> callback = new APICallBackCatcher<List<Execution>>();
        listExecutionsByJobAsync(jobName, callback, baseUri);
        if(!callback.isSuccess())
            throw callback.getError();
        return callback.getResult();
    }

    /**
     * List executions.
     * @param    jobName    Required parameter: The job that owns the executions to be fetched.
     * @return    Returns the void response from the API call 
     */
    public void listExecutionsByJobAsync(
                final String jobName,
                final APICallBack<List<Execution>> callBack,
                final String baseUri) {        
        
        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(baseUri);
        _queryBuilder.append("/executions/{job_name}");

        //process template parameters
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, new HashMap<String, Object>() {
            private static final long serialVersionUID = 5695317480165212020L;
            {
                    put( "job_name", jobName );
            }});
        //validate and preprocess url
        String _queryUrl = APIHelper.cleanUrl(_queryBuilder);

        //load all headers for the outgoing API request
        Map<String, String> _headers = new HashMap<String, String>() {
            private static final long serialVersionUID = 5365913808003735559L;
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
                            List<Execution> _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<List<Execution>>(){});

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