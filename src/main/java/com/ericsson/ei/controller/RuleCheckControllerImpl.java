package com.ericsson.ei.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ericsson.ei.jmespath.JmesPathInterface;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This class implements the Interface for JMESPath API, generated by RAML 0.8
 * Provides interaction with JmesPathInterface class (Generated with
 * springmvc-raml-parser v.0.10.11)
 * 
 */
@Component
@CrossOrigin
@Api(value = "jmespath")
@RequestMapping(value = "/jmespathrule/ruleCheck", produces = "application/json")
public class RuleCheckControllerImpl implements RuleCheckController {

    private static final Logger LOG = LoggerFactory.getLogger(SubscriptionControllerImpl.class);

    @Autowired
    JmesPathInterface jmesPathInterface;

    /**
     * This method interact with JmesPathInterface class method to evaluate a rule
     * on JSON expression.
     * 
     * @param arg1-
     *            takes in rule as a String that need to be evaluated
     * @param formArg-
     *            takes JSON as a String
     * @return return a String object
     * 
     */
    @Override
    @CrossOrigin
    @ApiOperation(value = "run rule on event")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> updateJmespathruleRuleCheck(String arg1, String formArg) {
        // TODO Auto-generated method stub
        String res = new String("[]");

        try {
            JSONObject jsonObj = new JSONObject(formArg);
            String jsonString = jsonObj.toString();
            res = jmesPathInterface.runRuleOnEvent(arg1, jsonString).toString();
            LOG.info("Query :" + arg1 + " executed Successfully");
            return new ResponseEntity<String>(res, HttpStatus.OK);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<String>(res, HttpStatus.BAD_REQUEST);
        }
    }

}
