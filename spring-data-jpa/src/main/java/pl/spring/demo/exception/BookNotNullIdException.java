package pl.spring.demo.exception;

import org.springframework.stereotype.Service;

@Service
public class BookNotNullIdException extends RuntimeException {
	private static final long serialVersionUID = 6345242969297838011L;
}
