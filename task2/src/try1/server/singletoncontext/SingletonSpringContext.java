package try1.server.singletoncontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import try1.server.config.UserConfiguration;

public final class SingletonSpringContext {
	private ApplicationContext context;
	private static SingletonSpringContext instance;

	private SingletonSpringContext() {
		this.context =  new AnnotationConfigApplicationContext(UserConfiguration.class);
	}

	public static SingletonSpringContext getInstance() {
		if (instance == null) {
			synchronized (SingletonSpringContext.class) {
				if (instance == null) {
					instance = new SingletonSpringContext();
				}
			}
		}
		return instance;
	}

	public ApplicationContext getContext() {
		return context;
	}

}
