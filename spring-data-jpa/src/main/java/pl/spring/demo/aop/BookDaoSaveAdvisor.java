package pl.spring.demo.aop;

import java.lang.reflect.Method;

import org.springframework.stereotype.Service;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.to.BookTo;

@Service
public class BookDaoSaveAdvisor extends BookDaoAdvisor {

	// TODO: BookDaoImpl's properties changed to protected (for this experiment only.)
	@Override
	public void before(Method method, Object[] objects, Object o) throws Throwable {
		if (hasAnnotation(method, o, NullableId.class)) {
			checkNotNullId(objects[0]);
			((BookTo) objects[0]).setId(((BookDaoImpl) o).nextSequence());
		}
	}
}
