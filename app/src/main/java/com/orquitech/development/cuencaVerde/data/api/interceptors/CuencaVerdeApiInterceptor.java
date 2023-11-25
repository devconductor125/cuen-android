package com.orquitech.development.cuencaVerde.data.api.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CuencaVerdeApiInterceptor implements Interceptor {

    private final MediaType MEDIA_JSON = MediaType.parse("application/json");
    private String monitoringMaintenance = "[{\n" +
            "\t\"geometry\": \"1\",\n" +
            "\t\"comment\": \"Comentario 1\",\n" +
            "\t\"due_date\": \"2018-03-14 00:00:00\",\n" +
            "\t\"feature\": \"{'geometry':{'coordinates':[[[-75.1813983,6.22294],[-75.1813983,6.2226483],[-75.1816,6.22172],[-75.1816,6.2213583],[-75.1817,6.22078],[-75.1817,6.2201583],[-75.182,6.2196483]]],'type':'Polygon'},'properties':{'AccionId':'5','Acciones':'Enriquecimiento Bosque de Ribera','Color':'#800000','hash':'1b03e320a18575d4484405b95f1fa2245fd35a323075def468590ef204b02b3e01821f14c5b86915473c8406d30648faa905cb0abce3fcddf9e60c96b1b7c8ef','MaterialId':'2','Material':'Pua poste inmunizado con plántula','OBJECTID':0,'Po':null,'SHAPE_Area':0,'SHAPE_Leng':0},'type':'Feature'}\"\n" +
            "}]";
    private String propertyManagement = "{\n" +
            "\t\"json\": {\n" +
            "\t\t\"type\": \"FeatureCollection\",\n" +
            "\t\t\"features\": [{\n" +
            "\t\t\t\"type\": \"Feature\",\n" +
            "\t\t\t\"properties\": {\n" +
            "\t\t\t\t\"OBJECTID\": 1,\n" +
            "\t\t\t\t\"CUENCA\": \"RGII\",\n" +
            "\t\t\t\t\"COD_MUNICI\": \"086\",\n" +
            "\t\t\t\t\"MUNICIPIO\": \"BELMIRA\",\n" +
            "\t\t\t\t\"VEREDA\": \"LA ALDAÑA\",\n" +
            "\t\t\t\t\"NOMBRE_PRE\": \"LA CRISTALINA\",\n" +
            "\t\t\t\t\"MICROCUENC\": null,\n" +
            "\t\t\t\t\"ESTADO_GP\": \"DESISTIO\",\n" +
            "\t\t\t\t\"SOCIO_COLA\": \"NO\",\n" +
            "\t\t\t\t\"AÑO_ACUER\": null,\n" +
            "\t\t\t\t\"PK_PREDIO\": \"0862001000000900076\",\n" +
            "\t\t\t\t\"FECHA_INGR\": null,\n" +
            "\t\t\t\t\"ID_PREDIO\": 1,\n" +
            "\t\t\t\t\"X\": 821719.790167,\n" +
            "\t\t\t\t\"Y\": 1222543.15446,\n" +
            "\t\t\t\t\"CONTRATO\": null,\n" +
            "\t\t\t\t\"Shape_Leng\": 5130.55327767,\n" +
            "\t\t\t\t\"Shape_Area\": 1134787.79374\n" +
            "\t\t\t},\n" +
            "\t\t\t\"geometry\": {\n" +
            "\t\t\t\t\"type\": \"Polygon\",\n" +
            "\t\t\t\t\"coordinates\": [\n" +
            "\t\t\t\t\t[\n" +
            "\t\t\t\t\t\t[-75.69454992401415,\n" +
            "\t\t\t\t\t\t\t6.608841838444936\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69449896819776,\n" +
            "\t\t\t\t\t\t\t6.608828949923359\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69417008159196,\n" +
            "\t\t\t\t\t\t\t6.60874625602963\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69377455726104,\n" +
            "\t\t\t\t\t\t\t6.608646596116417\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69355555649106,\n" +
            "\t\t\t\t\t\t\t6.608489628768025\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69352771603471,\n" +
            "\t\t\t\t\t\t\t6.608548841235036\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69350039150396,\n" +
            "\t\t\t\t\t\t\t6.608605482248493\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69348533236182,\n" +
            "\t\t\t\t\t\t\t6.608636494123437\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69347780376309,\n" +
            "\t\t\t\t\t\t\t6.608652160499405\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69344736615045,\n" +
            "\t\t\t\t\t\t\t6.608714668870632\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69342878319111,\n" +
            "\t\t\t\t\t\t\t6.608753109562734\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69339402121508,\n" +
            "\t\t\t\t\t\t\t6.608824822795469\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69334419917067,\n" +
            "\t\t\t\t\t\t\t6.608927547905552\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69318553697806,\n" +
            "\t\t\t\t\t\t\t6.609254856946889\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69300649600828,\n" +
            "\t\t\t\t\t\t\t6.609624107873369\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69293708254415,\n" +
            "\t\t\t\t\t\t\t6.609767104623685\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69309273402381,\n" +
            "\t\t\t\t\t\t\t6.609813723999116\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69315159230476,\n" +
            "\t\t\t\t\t\t\t6.609831397531982\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69315478193319,\n" +
            "\t\t\t\t\t\t\t6.609863861478395\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69315835379057,\n" +
            "\t\t\t\t\t\t\t6.60990212991307\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69316042152121,\n" +
            "\t\t\t\t\t\t\t6.609924256921736\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69316201751496,\n" +
            "\t\t\t\t\t\t\t6.609940851805487\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69318066989365,\n" +
            "\t\t\t\t\t\t\t6.609958297921002\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69320323539596,\n" +
            "\t\t\t\t\t\t\t6.609979360476241\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.6932206148401,\n" +
            "\t\t\t\t\t\t\t6.609995632041021\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69324818561837,\n" +
            "\t\t\t\t\t\t\t6.61002148526532\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69325210701469,\n" +
            "\t\t\t\t\t\t\t6.610025144161823\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69326838789365,\n" +
            "\t\t\t\t\t\t\t6.610029913588854\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.6932798798235,\n" +
            "\t\t\t\t\t\t\t6.610033320122157\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69338960085811,\n" +
            "\t\t\t\t\t\t\t6.61006551588558\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.6935537518901,\n" +
            "\t\t\t\t\t\t\t6.610114006570596\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69361143476318,\n" +
            "\t\t\t\t\t\t\t6.610131020393413\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69361956798602,\n" +
            "\t\t\t\t\t\t\t6.610132852390924\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.6936122145284,\n" +
            "\t\t\t\t\t\t\t6.610176839996832\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69355881181293,\n" +
            "\t\t\t\t\t\t\t6.610280904598205\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69348662230271,\n" +
            "\t\t\t\t\t\t\t6.610385154947468\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69342106733404,\n" +
            "\t\t\t\t\t\t\t6.610438930945523\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t[-75.69337215594578,\n" +
            "\t\t\t\t\t\t\t6.61047277258645\n" +
            "\t\t\t\t\t\t]\n" +
            "\t\t\t\t\t]\n" +
            "\t\t\t\t]\n" +
            "\t\t\t}\n" +
            "\t\t}]\n" +
            "\t}\n" +
            "}";

    private String siembra = "{\n" +
            "\t\"id\": 24444,\n" +
            "\t\"geojson\": {\n" +
            "\t\t\"type\": \"FeatureCollection\",\n" +
            "\t\t\"features\": [{\n" +
            "\t\t\t\"type\": \"Feature\",\n" +
            "\t\t\t\"properties\": {\n" +
            "\t\t\t\t\"Name\": \"00014\",\n" +
            "\t\t\t\t\"description\": null,\n" +
            "\t\t\t\t\"altitudeMode\": \"clampToGround\",\n" +
            "\t\t\t\t\"SECTOR\": \"2\",\n" +
            "\t\t\t\t\"OBJECTID_1\": \"4186\",\n" +
            "\t\t\t\t\"TIPO\": \"\",\n" +
            "\t\t\t\t\"NUMERORADI\": \"0\",\n" +
            "\t\t\t\t\"CORREGIMIE\": \"002\",\n" +
            "\t\t\t\t\"TOTAL_UNID\": \"0\",\n" +
            "\t\t\t\t\"PK_PREDIOS\": \"0022002000000900014\",\n" +
            "\t\t\t\t\"MANZANA_VE\": \"0009\",\n" +
            "\t\t\t\t\"PREDIOS\": \"00014\",\n" +
            "\t\t\t\t\"BARRIO\": \"000\",\n" +
            "\t\t\t\t\"MUNICIPIO\": \"002\",\n" +
            "\t\t\t\t\"feature_type\": \"MultiPolygon\",\n" +
            "\t\t\t\t\"Color\": \"#87D7E0\",\n" +
            "\t\t\t\t\"FillColor\": \"#8087D7E0\",\n" +
            "\t\t\t\t\"hash\": \"ASDFGHJKLÑWQERTYUIOCVBNMFVGHJ4567FDGHJCVBNFGHJKZXCVNBM\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"geometry\": {\n" +
            "\t\t\t\t\"type\": \"MultiPolygon\",\n" +
            "\t\t\t\t\"coordinates\": [\n" +
            "\t\t\t\t\t[\n" +
            "\t\t\t\t\t\t[\n" +
            "\t\t\t\t\t\t\t[-75.5122367197763, 5.73307870321942],\n" +
            "\t\t\t\t\t\t\t[-75.51204028244739, 5.733076235774815],\n" +
            "\t\t\t\t\t\t\t[-75.51165201694296, 5.733071669719631],\n" +
            "\t\t\t\t\t\t\t[-75.51139911921001, 5.733100863235431],\n" +
            "\t\t\t\t\t\t\t[-75.51118571914043, 5.733136819092151],\n" +
            "\t\t\t\t\t\t\t[-75.51103956041457, 5.733141981712344],\n" +
            "\t\t\t\t\t\t\t[-75.51085427495595, 5.733118345003518],\n" +
            "\t\t\t\t\t\t\t[-75.51085725905358, 5.733216498338091],\n" +
            "\t\t\t\t\t\t\t[-75.51087050164611, 5.733306500838211],\n" +
            "\t\t\t\t\t\t\t[-75.51089035557054, 5.733404597468037],\n" +
            "\t\t\t\t\t\t\t[-75.510941724582, 5.733526873109478],\n" +
            "\t\t\t\t\t\t\t[-75.5110498707243, 5.733710365532931],\n" +
            "\t\t\t\t\t\t\t[-75.51108882340937, 5.733805008043909],\n" +
            "\t\t\t\t\t\t\t[-75.51110447866328, 5.733893260667086],\n" +
            "\t\t\t\t\t\t\t[-75.51110639658313, 5.733949323724811],\n" +
            "\t\t\t\t\t\t\t[-75.51133237853985, 5.734181029535354],\n" +
            "\t\t\t\t\t\t\t[-75.5115120784704, 5.7343462869775],\n" +
            "\t\t\t\t\t\t\t[-75.51188887423244, 5.73465959559109],\n" +
            "\t\t\t\t\t\t\t[-75.51203498297065, 5.734789638518921],\n" +
            "\t\t\t\t\t\t\t[-75.51213805336846, 5.734664044305311],\n" +
            "\t\t\t\t\t\t\t[-75.51229795480577, 5.734434077432673],\n" +
            "\t\t\t\t\t\t\t[-75.51244085082095, 5.734238801651455],\n" +
            "\t\t\t\t\t\t\t[-75.51213710210685, 5.734242820150829],\n" +
            "\t\t\t\t\t\t\t[-75.5121392651329, 5.733888998498904],\n" +
            "\t\t\t\t\t\t\t[-75.51216303749855, 5.733576803377737],\n" +
            "\t\t\t\t\t\t\t[-75.51220938330012, 5.733291159999384],\n" +
            "\t\t\t\t\t\t\t[-75.5122367197763, 5.73307870321942]\n" +
            "\t\t\t\t\t\t]\n" +
            "\t\t\t\t\t]\n" +
            "\t\t\t\t]\n" +
            "\t\t\t}\n" +
            "\t\t}]\n" +
            "\t}\n" +
            "}";

    public CuencaVerdeApiInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String path = request.url().encodedPath();
        String json = "";

        switch (path) {
            case "/monitoringMaintenance":
                json = monitoringMaintenance;
                break;
            case "/generals/get/propertyManagement":
                json = propertyManagement;
                break;
            case "/siembra/24444":
                json = siembra;
                break;
        }

        return new Response.Builder()
                .message(json)
                .body(ResponseBody.create(MEDIA_JSON, json))
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .code(200)
                .build();
    }
}
