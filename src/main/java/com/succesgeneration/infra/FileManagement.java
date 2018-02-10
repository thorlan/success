package com.succesgeneration.infra;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Component
public class FileManagement {

	@Autowired
	private AmazonS3 amazonS3;
	private static final String BUCKET = "successgeneration-teste";

	public String write(MultipartFile file) {
		try {
			
			String fileName = file.getOriginalFilename() + LocalDateTime.now();
			
			amazonS3.putObject(new PutObjectRequest(BUCKET, fileName,
					file.getInputStream(), null)
					.withCannedAcl(CannedAccessControlList.PublicRead));
			
			return "http://s3.amazonaws.com/" + BUCKET + "/" + fileName; 
					
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String caminho) {
		
		String fileName = caminho.substring(caminho.lastIndexOf("/")+1, caminho.length());
		
		System.out.println("-------------------------------------");
		System.out.println("BUKCET :" + BUCKET);
		System.out.println("fileName : " + fileName);
		System.out.println("caminho : " + caminho);
		System.out.println("-------------------------------------");
		
		amazonS3.deleteObject(BUCKET,fileName);
	}
	

	/*@Autowired
	private HttpServletRequest request;
	
	private Logger logger = Logger.getLogger(FileManagement.class);
	
	public String save(String baseFolder, MultipartFile file) {
	    try {
	    	
	    	String originalFileName = LocalDateTime.now()+ "-" + file.getOriginalFilename();
	    	String path = getFullPath(originalFileName);
	        file.transferTo(new File(path));
	 
	        return originalFileName;

	    } catch (IllegalStateException | IOException e) {
	        throw new RuntimeException(e);
	    }
	}

	public void delete(String name) {
		String path = getFullPath(name);
		Path fileToDeletePath = Paths.get(path);
		try {
			Files.delete(fileToDeletePath);
			logger.info("Arquivo excluido com sucesso");
		} catch (IOException e) {
			logger.error("ERRO ao excluir arquivo " + fileToDeletePath );
			e.printStackTrace();
		}
	
			
	}
	
	private String getFullPath(String originalFileName) {
		
		
		String realPath = "/home/thiago/Documentos/workspace/succesgeneration/"
				+ "succesgeneration/src/main/webapp/resources/imagens/servicos-fotos";
		//String para desenvolvimento. Essa que estava funcionando!
		
		// String realPath = request.getServletContext().getRealPath("/"+baseFolder);
    	//Testar no PC, quando for para o servidor, esse caminho vai mudar!!!
		
		String path = realPath + "/" + originalFileName;
		return path;
	}*/
	
}
