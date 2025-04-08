package com.projeto.web.swagger;

import org.springframework.http.ResponseEntity;

import com.projeto.models.data.LoginRequest;
import com.projeto.models.data.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Login",description = "Endpoints para o gerenciamento de login do usuário")
public interface LoginRestControllerApi {
	
	@Operation(summary = "Retorna o login do Usuario", description = "Login de usuários",responses = {
		    @ApiResponse(
		        responseCode="200",
		        content = {
		           @Content( mediaType = "application/json",array = @ArraySchema( schema=@Schema(implementation = LoginResponse.class)))		 
		    }),
		    @ApiResponse(description="Bad Request", responseCode="400", content=@Content),
		    @ApiResponse(description="Unauthorized", responseCode="401", content=@Content),
		    @ApiResponse(description="Not Found", responseCode="404", content=@Content),
		    @ApiResponse(description="Internal Error", responseCode="500", content=@Content),  
		})
	public ResponseEntity<?> login(LoginRequest login);
	
	@Operation(summary = "Recuperar token do Usuario", description = "Login de usuários",responses = {
		    @ApiResponse(
		        responseCode="200",
		        content = {
		           @Content( mediaType = "application/json",array = @ArraySchema( schema=@Schema(implementation = LoginResponse.class)))		 
		    }),
		    @ApiResponse(description="Bad Request", responseCode="400", content=@Content),
		    @ApiResponse(description="Unauthorized", responseCode="401", content=@Content),
		    @ApiResponse(description="Not Found", responseCode="404", content=@Content),
		    @ApiResponse(description="Internal Error", responseCode="500", content=@Content),  
		})
	public ResponseEntity<?> refreshkToken(String token);
	
	@Operation(summary = "Desconectar Usuario", description = "Login de usuários",responses = {
		    @ApiResponse(
		        responseCode="200",
		        content = {
		           @Content( mediaType = "application/json",array = @ArraySchema( schema=@Schema(implementation = LoginResponse.class)))		 
		    }),
		    @ApiResponse(description="Bad Request", responseCode="400", content=@Content),
		    @ApiResponse(description="Unauthorized", responseCode="401", content=@Content),
		    @ApiResponse(description="Not Found", responseCode="404", content=@Content),
		    @ApiResponse(description="Internal Error", responseCode="500", content=@Content),  
		})
	public ResponseEntity<?> Logout(String token);
	
	@Operation(summary = "Validar token do Usuario", description = "Login de usuários",responses = {
		    @ApiResponse(
		        responseCode="200",
		        content = {
		           @Content( mediaType = "application/json",array = @ArraySchema( schema=@Schema(implementation = LoginResponse.class)))		 
		    }),
		    @ApiResponse(description="Bad Request", responseCode="400", content=@Content),
		    @ApiResponse(description="Unauthorized", responseCode="401", content=@Content),
		    @ApiResponse(description="Not Found", responseCode="404", content=@Content),
		    @ApiResponse(description="Internal Error", responseCode="500", content=@Content),  
		})
	public ResponseEntity<?> validarToken(String access_token, String refreshToken);
	
	
}
