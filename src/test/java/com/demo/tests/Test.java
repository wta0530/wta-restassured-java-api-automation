package com.demo.tests;

import java.io.IOException;

import com.demo.utils.Configuration;
import com.github.javafaker.Faker;

public class Test {

	public static void main(String[] args) throws IOException {
		Faker data = new Faker();
		System.out.println(data.numerify("+91975#######"));
		System.out.println(Configuration.paramsMap("providerName").getClass().getSimpleName());
		System.out.println(Configuration.paramsMap("providerId").getClass().getSimpleName());
		System.out.println(Configuration.paramsMap("providerDescription").getClass().getSimpleName());
		System.out.println("suffix Id :: "+Configuration.paramsMap("lSuffixId")+" :: data type :: "+Configuration.paramsMap("lSuffixId").getClass().getSimpleName());
	}
}
