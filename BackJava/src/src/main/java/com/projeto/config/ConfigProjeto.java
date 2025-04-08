package com.projeto.config;

public class ConfigProjeto {

	public static final String DIRETORIO_FOTOS = "/cocao/catalogo";
	
	public static final String PAGE_NUMBER = "0";
	
	public static final String PAGE_SIZE = "10";
	
	public static final String PROPS = "id";
	
	public static final String SORT = "asc";
	
	public static final String SECRETKEY = "PalaVrAS3cr3tAD3AceSso"; //senha para os token 
	
	public static final String []
			WHITE_LIST_URL = {
					"/v3/api-docs",
					"/configuratio/ui",
					"/swagger-resources/**",
					"/configuration/security",
					"/swagger-ui-html",
					"/webjars/**"
			}; 
	

}
