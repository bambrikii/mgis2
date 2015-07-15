package ru.sovzond.mgis2.web.isogd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sovzond.mgis2.isogd.business.classifiers.representation.RepresentationFormatBean;
import ru.sovzond.mgis2.isogd.business.document.parts.DocumentContentBean;
import ru.sovzond.mgis2.isogd.classifiers.documents.representation.RepresentationFormat;
import ru.sovzond.mgis2.isogd.document.DocumentContent;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Alexander Arakelyan on 15.07.15.
 */
@RestController
@RequestMapping("/isogd/documentContents")
@Scope("session")
public class DocumentContentRESTController {

    @Autowired
    private DocumentContentBean documentContentBean;

    @Autowired
    private RepresentationFormatBean representationFormatBean;


    @RequestMapping(value = "/upload", headers = "Accept=*/*", produces = "application/json", method = RequestMethod.POST)
    @Transactional
    @ResponseBody
    public String uploadCommonContent(@RequestBody MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        List<RepresentationFormat> list = representationFormatBean.findByFormat(contentType);
        switch (list.size()) {
            case 0:
                throw new IllegalArgumentException("NO_REPRESENTATION_FORMAT_FOUND: " + contentType);
            case 1:
                RepresentationFormat representationFormat = list.get(0);
                DocumentContent documentContent = new DocumentContent();
                documentContent.setRepresentationFormat(representationFormat);
                documentContent.setFileName(file.getOriginalFilename());
                try {
                    documentContent.setBytes(file.getBytes());
                } catch (IOException ex) {
                    throw ex;
                }
                documentContentBean.save(documentContent);
                ObjectMapper mapper = new ObjectMapper();
                try {
                    return mapper.writeValueAsString(documentContent.clone());
                } catch (JsonProcessingException ex) {
                    throw ex;
                }
            default:
                throw new IllegalArgumentException("MORE_THAN_ONE_REPRESENTATION_FORMATS_FOUND: " + contentType);
        }
    }

    @RequestMapping(value = "/{contentId}/preview")
    @Transactional
    public ResponseEntity<byte[]> preview(@PathVariable("contentId") Long contentId) {
        DocumentContent documentContent = documentContentBean.load(contentId);
        Set<String> formats = documentContent.getRepresentationFormat().getFormats();
        MediaType defaultFormat = MediaType.IMAGE_PNG;
        for (String format : formats) {
            String format2 = format.toLowerCase();
            if (format2.contains("jpeg") || format2.contains("jpg")) {
                defaultFormat = MediaType.IMAGE_JPEG;
                break;
            } else if (format2.contains("png")) {
                defaultFormat = MediaType.IMAGE_PNG;
                break;
            } else if (format2.contains("gif")) {
                defaultFormat = MediaType.IMAGE_GIF;
                break;
            } else if (format2.contains("doc")) {
                defaultFormat = MediaType.IMAGE_PNG;
                break;
            } else if (format2.contains("pdf")) {
                defaultFormat = MediaType.IMAGE_PNG;
                break;
            }
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(defaultFormat);
        return new ResponseEntity<>(documentContent.getBytes(), headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{contentId}/download")
    @Transactional
    public ResponseEntity<byte[]> download(@PathVariable("contentId") Long contentId) {
        DocumentContent documentContent = documentContentBean.load(contentId);
        Set<String> formats = documentContent.getRepresentationFormat().getFormats();
        MediaType defaultFormat = MediaType.IMAGE_PNG;
        for (String format : formats) {
            String format2 = format.toLowerCase();
            if (format2.contains("jpeg") || format2.contains("jpg")) {
                defaultFormat = MediaType.IMAGE_JPEG;
                break;
            } else if (format2.contains("png")) {
                defaultFormat = MediaType.IMAGE_PNG;
                break;
            } else if (format2.contains("gif")) {
                defaultFormat = MediaType.IMAGE_GIF;
                break;
            } else if (format2.contains("doc")) {
                defaultFormat = MediaType.APPLICATION_OCTET_STREAM;
                break;
            } else if (format2.contains("pdf")) {
                defaultFormat = MediaType.APPLICATION_OCTET_STREAM;
                break;
            }
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(defaultFormat);
        return new ResponseEntity<>(documentContent.getBytes(), headers, HttpStatus.CREATED);
    }
}
