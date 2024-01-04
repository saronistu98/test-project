package json_patch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

public class JSONPatchUsage {
    public static void main(String[] args) throws IOException, JsonPatchException {

        ObjectMapper objectMapper = new ObjectMapper();
//        json_patch.ExternalProductRequestDto dtoWithModifications = getDtoWithModifications();
        ExternalProductRequestDto initialDto = getInitialDto();
//        String s = objectMapper.writeValueAsString(dtoWithModifications);
//
//        System.out.println(s);

        String json = "{\"description\":\"Descriere noua si actualizata\"}";
        String jsonPatch = "[{\n" +
                "    \"op\": \"replace\",\n" +
                "    \"path\": \"/description\",\n" +
                "    \"value\": \"Descriere noua si mai buna\"\n" +
                "}]";
        JsonNode node = objectMapper.readTree(jsonPatch);
        JsonNode node2 = objectMapper.readTree(json);
        JsonPatch patch = JsonPatch.fromJson(node);
        JsonMergePatch mergePatch = JsonMergePatch.fromJson(node2);
        JsonNode patched = mergePatch.apply(objectMapper.convertValue(initialDto, JsonNode.class));
        ExternalProductRequestDto newDto = objectMapper.treeToValue(patched, ExternalProductRequestDto.class);

        System.out.println();

    }

    private static ExternalProductRequestDto getDtoWithModifications() {
        ExternalProductRequestDto dto = new ExternalProductRequestDto();
        dto.setDescription("Descriere noua si mai buna");
        return dto;
    }

    private static ExternalProductRequestDto getInitialDto() {
        ExternalProductRequestDto dto = new ExternalProductRequestDto();
        dto.setDescription("Descriere curenta");
        dto.setProductName("Ford Mustang 2022");
        dto.setEan("3271853621");
        return dto;
    }
}

@Getter
@Setter
class ExternalProductRequestDto {

    private String productName;
    private String assortment;
    private String manufacturer;
    private Boolean manufacturerInName;
    private Long weight;
    private String measurementUnit;
    private String category;
    private List<String> tags;
    private String ean;
    private List<String> images;
    private String description;
    private String originCountry;
    private String storingDetails;
    private String usageDetails;
    private String ingredients;
    private String allergens;
    private Boolean isInstantUse;
    private String productInfo;
    private String cim;

}
