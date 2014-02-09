package br.com.cotasparlamentares.normalizacotas.execute;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import br.com.cotasparlamentares.normalizacotas.domain.SingletonParametros;

@Component
public class InicializadorSpring {

	private static final String CONFIG_PATH = "classpath*:application-config.xml";

	// Teste a partir dessa classe. Ao iniciar o spring, o service anotado com @PostConstruct vai iniciar também. 
	public static void main(String[] args) {
		
		if(args.length==1) {
		
			int i=0;
			String strCaminhoLog = args[i++];
			
			// Singleton
			SingletonParametros sParametros = SingletonParametros.getInstance();
			sParametros.setCaminhoLog(strCaminhoLog);
			
			new ClassPathXmlApplicationContext(CONFIG_PATH);
			
		} else {
			printUsage();
		}
		
	}
	
	public static void printUsage() {
		System.out.println("Impossível iniciar sincronizador! Passe 1 parâmetro: "
				+ "\n1) Caminho completo do arquivo de log. "
				+ "\n Exemplo: java -jar normalizacotas.jar c:\\log.xml");
	}

}