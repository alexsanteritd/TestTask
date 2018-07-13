package try1.server.singletoncontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import try1.server.config.UserConfiguration;

public final class SingletonSpringContext {
	private static ApplicationContext context;

	private SingletonSpringContext() {
		SingletonSpringContext.context = new AnnotationConfigApplicationContext(UserConfiguration.class);
	}


	public ApplicationContext getContext() {
		return context;
	}

	public static synchronized <T> T getBean(Class<T> classType) {
		if(context==null) {
			new SingletonSpringContext();
		}
		return context.getBean(classType);
	}

}
