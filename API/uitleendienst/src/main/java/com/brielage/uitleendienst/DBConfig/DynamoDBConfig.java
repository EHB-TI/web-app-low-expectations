package com.brielage.uitleendienst.DBConfig;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories (basePackages = "com.brielage.uitleendienst.repositories")
public class DynamoDBConfig {
    @Value ("${amazon.region}")
    private String amazonRegion;
    @Value ("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;
    @Value ("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;
    @Value ("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    private static final Logger logger = LoggerFactory.getLogger(DynamoDBConfig.class.getName());

    @Bean
    public AmazonDynamoDB amazonDynamoDB () {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());

        // otherwise it errors when trying to connect
        amazonDynamoDB.setRegion(Region.getRegion(Regions.valueOf(amazonRegion)));

        if (!StringUtils.isNullOrEmpty(amazonDynamoDBEndpoint))
            amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);

        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials () {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }
}
