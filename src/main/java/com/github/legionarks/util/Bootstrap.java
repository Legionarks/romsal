package com.github.legionarks.util;

import java.math.BigDecimal;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.legionarks.model.Contact;
import com.github.legionarks.model.Location;
import com.github.legionarks.model.currency.CurrencyType;
import com.github.legionarks.model.currency.Rate;
import com.github.legionarks.model.property.Feature;
import com.github.legionarks.model.property.Media;
import com.github.legionarks.model.property.Property;
import com.github.legionarks.model.property.attribute.AttributeType;
import com.github.legionarks.model.property.attribute.PropertyAttribute;
import com.github.legionarks.model.property.category.CategoryType;
import com.github.legionarks.model.property.type.PropertyType;
import com.github.legionarks.service.ContactService;
import com.github.legionarks.service.CurrencyService;
import com.github.legionarks.service.PropertyService;
import com.github.legionarks.service.UserService;

import io.quarkus.runtime.Startup;

@Startup
@Singleton
public class Bootstrap {

    @Inject
    PropertyService propertyService;

    @Inject
    UserService userService;

    @Inject
    ContactService contactService;

    @Inject
    CurrencyService currencyService;

    public Bootstrap() {
    }

    @PostConstruct
    public void init() {
        currencies();
        users();
        contact();
        properties();
    }

    private void currencies() {        
        currencyService.currencies();
        currencyService.persist(new Rate(currencyService.getCurrencyDao().find(CurrencyType.USD), currencyService.getCurrencyDao().find(CurrencyType.DOP), BigDecimal.valueOf(57.50)));
    }

    private void users() {
        userService.roles();

        userService.add("user", "user", "USER");
        userService.add("admin", "admin", "ADMIN");
    }

    private void contact() {
        Contact contact;
        Location location;

        contact = new Contact();
        location = new Location();
        contact.setCity("Santiago de los Caballeros");
        contact.setAddress("C/ Parada Vieja, Res. Mar??a Alejandra I 2do Nivel");
        contact.setPhone("(809) 820 - 0897");
        location.set(19.4510266F, -70.6517572F);
        contact.setLocation(location);
        contactService.getData().create(contact);
    }

    private void properties() {
        Property property;

        propertyService.types();
        propertyService.categories();
        propertyService.attributes();

        // QUINTA DON FELIX GIL
        property = new Property();
        property.setName("Quinta Don Felix Gil");
        property.setDescription("Dos vivienda, Segunda etapa, Tercera etapa, Casas unifamiliar, Un nivel");
        property.setAddress("La Romana");
        property.setPrice(BigDecimal.valueOf(2800000));
        property.setCurrency(currencyService.getCurrencyDao().find(CurrencyType.DOP));
        property.setOutstanding(true);
        property.setSummary(
                "<p>Solar de 153.00 mts2</p><p><strong><em>Fecha de entrega segunda etapa:</em></strong> agosto 2021</p><p><strong><em>Fecha de entrega segunda etapa:</em></strong> agosto 2022</p><p><strong><em>Entrega: </em></strong>octubre 2021</p><p><strong><em>Plan de pago:</em></strong> Separaci&oacute;n: US$5,000</p><p><strong><em>&nbsp;</em></strong></p><p><strong><em>Terminaciones::</em></strong></p><ul><li>Pisos en porcelanatos de fabricaci&oacute;n extranjera.</li><li>Cocina modular</li><li>Tope de granito natural</li><li>Mampara de vidrio sin puerta en ba&ntilde;os.</li></ul><p>&middot;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Ventanas en vidrio y aluminio</p>");
        property.setType(propertyService.getTypeDao().find(PropertyType.HOUSE.name()));
        property.setCategory(propertyService.getCategoryDao().find(CategoryType.SELL.name()));
        property.setLocation(new Location(19.4279190F, -70.6570360F));
        property.setAttributes(Set.of(
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.ROOM.name()),
                        (short) 3),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.BATH.name()),
                        (short) 2),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.PARKING.name()),
                        (short) 2),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.AREA.name()),
                        (short) 78)));
        property.setFeatures(
                Set.of(new Feature(property, "Sala"), new Feature(property, "Comedor"), new Feature(property, "Cocina"),
                        new Feature(property, "Balc??n"), new Feature(property, "??rea de lavado")));
        propertyService.getPropertyDao().create(property);

        // Garden Tower
        property = new Property();
        property.setName("Garden Tower");
        property.setDescription("Etapa # 1 A-2 / C-3    Etapa #2 C-4 /H-2");
        property.setAddress("Sabaneta Las Palomas, Santiago de los Caballeros");
        property.setPrice(BigDecimal.valueOf(123000.00));
        property.setCurrency(currencyService.getCurrencyDao().find(CurrencyType.USD));
        property.setOutstanding(true);
        property.setSummary(
                "<p><strong><em>Fecha de entrega:</em></strong> Octubre 2021</p><p><strong><em>Plan de pago:</em></strong> Separaci&oacute;n: US$5,000</p><p><strong><em>Inicial:</em></strong> 30% (Durante el proceso de construcci&oacute;n)</p><p><strong><em>Terminaciones:</em></strong></p><ul><li>Pisos en porcelanatos de fabricaci&oacute;n extranjera.</li><li>Madera preciosa en puertas</li><li>Tope de granito natural</li><li>Mampara de vidrio sin puerta en ba&ntilde;os.</li><li>Ventanas en vidrio y aluminio</li></ul><p><strong><em>Amenidades:</em></strong></p><p>&Aacute;rea Social</p><p>Piscina</p><p>&Aacute;rea de Juegos Infantiles</p><p>Parque al Aire Libre</p><p>Ascensor</p><p>Lobby</p><p>Acceso Controlado.</p><p>Planta El&eacute;ctrica Full.</p>");
        property.setType(propertyService.getTypeDao().find(PropertyType.BUILDING.name()));
        property.setCategory(propertyService.getCategoryDao().find(CategoryType.SELL.name()));
        property.setLocation(new Location(19.4279190F, -70.6570360F));
        property.setAttributes(Set.of(
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.ROOM.name()),
                        (short) 3),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.BATH.name()),
                        (short) 2),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.PARKING.name()),
                        (short) 2),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.AREA.name()),
                        (short) 110)));
        property.setFeatures(Set.of(new Feature(property, "Sala"), new Feature(property, "Comedor"),
                new Feature(property, "Cocina"), new Feature(property, "Balc??n"),
                new Feature(property, "??rea de lavado"), new Feature(property, "Salida de emergencia")));
        property.setMedias(Set.of(new Media(property, "1.jpeg"), new Media(property, "2.jpeg"), new Media(property, "3.jpeg"), new Media(property, "4.jpeg"), new Media(property, "5.jpeg"), new Media(property, "6.jpeg"), new Media(property, "7.jpeg"), new Media(property, "8.jpeg"), new Media(property, "9.jpeg")));
        propertyService.getPropertyDao().create(property);

        // Rouse Tower
        property = new Property();
        property.setName("Rouse Tower");
        property.setDescription(
                "Este lujoso residencial estar?? localizado en una zona privilegiada de la ciudad coraz??n, urbanizaci??n Thomen. Lugar tranquilo, seguro y estrat??gicamente ubicado; f??cil acceso a las principales v??as de tr??nsito, cerca de universidades, hospitales, aeropuerto, supermercados y centros comerciales. Contar?? con 20 apartamentos y 4 penthouses distribuidos en un edificio de siete (7) niveles y cuatro apartamentos por cada nivel.");
        property.setAddress("Urbanizaci??n Thomen, Santiago de los Caballeros");
        property.setPrice(BigDecimal.valueOf(114000.00));
        property.setCurrency(currencyService.getCurrencyDao().find(CurrencyType.USD));
        property.setOutstanding(true);
        property.setSummary(
                "<p><strong><em>Nivel</em></strong>: B4</p><p><strong><em>Fecha de entrega</em></strong>: Noviembre 2021</p><p><strong><em>Plan de pago</em></strong>: Se aparta con US$2,000.00 (30% de inicial en 6 meses)</p><p><strong><em>Terminaciones:</em></strong></p><ul><li>Madera preciosa de roble</li><li>Pisos de porcelanato importado</li><li>Cocina con cenefas; luces ojos de buey leds, grifer&iacute;a en primera; Tope en granito natural brasile&ntilde;o</li><li>Ba&ntilde;os revestidos de cer&aacute;mica importada; lavamanos sobre mueble de madera y topes de m&aacute;rmol; grifer&iacute;a en primera.</li></ul><p><strong><em>Amenidades: </em></strong></p><ul><li>Gimnasio</li><li>&Aacute;rea infantil</li><li>&Aacute;reas Verdes</li><li>Rooftop</li><li>Gazebo para actividades.</li><li>Ascensor</li><li>Parqueo soterrados</li><li>Seguridad 24 horas</li><li>Sistema de alarma</li><li>Cerco Electrico</li><li>Planta El&eacute;ctrica</li><li>Calentador de Gas.</li></ul>");
        property.setType(propertyService.getTypeDao().find(PropertyType.BUILDING.name()));
        property.setCategory(propertyService.getCategoryDao().find(CategoryType.SELL.name()));
        property.setLocation(new Location(19.4250424F, -70.6732004F));
        property.setAttributes(Set.of(
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.ROOM.name()),
                        (short) 3),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.BATH.name()),
                        (short) 4),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.PARKING.name()),
                        (short) 2),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.AREA.name()),
                        (short) 114)));
        property.setFeatures(Set.of(new Feature(property, "Sala de estar y principal"),
                new Feature(property, "Terraza con vista panor??mica"), new Feature(property, "Cocina"),
                new Feature(property, "Comedor"), new Feature(property, "??rea de lavado")));
        property.setMedias(Set.of(new Media(property, "1.jpg"), new Media(property, "2.jpg"),
                        new Media(property, "3.jpg"), new Media(property, "4.jpg"), new Media(property, "5.jpg"),
                        new Media(property, "6.jpg"), new Media(property, "7.jpg"), new Media(property, "8.jpg"),
                        new Media(property, "9.jpg"), new Media(property, "10.jpg"), new Media(property, "11.jpg"),
                        new Media(property, "12.jpg"), new Media(property, "13.jpg"), new Media(property, "14.jpg")));
        propertyService.getPropertyDao().create(property);

        // Residencial Alexander 3
        property = new Property();
        property.setName("Residencial Alexander 3");
        property.setDescription("Dos niveles vivienda unfamiliar # 38");
        property.setAddress("Villa Hermosa, La Romana");
        property.setPrice(BigDecimal.valueOf(3600000));
        property.setCurrency(currencyService.getCurrencyDao().find(CurrencyType.DOP));
        property.setOutstanding(true);
        property.setSummary(
                "<p>Solar de 180.90 mts2</p><p><strong><em>Plan de pago</em></strong><em>:</em> Separaci&oacute;n: US$5,000</p><p><strong><em>Terminaciones</em></strong><em>:</em></p><ul><li>Pisos en cer&aacute;mica de fabricaci&oacute;n extranjera.</li><li>Cocina modular</li><li>Tope de granito natural</li><li>Mampara de vidrio sin puerta en ba&ntilde;os.</li><li>Ventanas en vidrio y aluminio</li></ul>");
        property.setType(propertyService.getTypeDao().find(PropertyType.BUILDING.name()));
        property.setCategory(propertyService.getCategoryDao().find(CategoryType.SELL.name()));
        property.setLocation(new Location(19.4250424F, -70.6732004F));
        property.setAttributes(Set.of(
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.ROOM.name()),
                        (short) 3),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.BATH.name()),
                        (short) 2),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.PARKING.name()),
                        (short) 2),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.AREA.name()),
                        (short) 112)));
        property.setFeatures(
                Set.of(new Feature(property, "Sala"), new Feature(property, "Balc??n"), new Feature(property, "Cocina"),
                        new Feature(property, "Comedor"), new Feature(property, "??rea de lavado")));
        propertyService.getPropertyDao().create(property);

        // Residencial Cadiz XIV
        property = new Property();
        property.setName("Residencial Cadiz XIV");
        property.setDescription("Nivel: C3");
        property.setAddress("Calle H, Urbanizaci??n los ??lamos, Santiago de los Caballeros");
        property.setPrice(BigDecimal.valueOf(135000));
        property.setCurrency(currencyService.getCurrencyDao().find(CurrencyType.USD));
        property.setOutstanding(true);
        property.setSummary(
                "<p><strong><em>Fecha de entrega</em></strong>: Octubre 2021 (fecha tentativa ya que est&aacute;n atrasados )</p><p><strong><em>Plan de pago</em></strong>: Apartarlo con el 10%. Antes de 2 meses completar el 30%(US$ 40,500.00)</p><p><strong><em>Terminaciones</em></strong>:</p><ul><li>Pisos en porcelanatos de fabricaci&oacute;n extranjera.</li><li>Madera preciosa en puertas</li><li>Tope de granito natural</li><li>Mampara de vidrio sin puerta en ba&ntilde;os.</li><li>Ventanas en vidrio y aluminio</li></ul><p><strong><em>Amenidades</em></strong>:</p><ul><li>Residencial cerrado con caseta de seguridad.</li><li>Port&oacute;n el&eacute;ctrico.</li><li>Gas com&uacute;n con medidores.</li><li>Escalera de emergencia.</li><li>Intercom.</li><li>Cisterna.</li><li>Escalera de emergencia.</li><li>Inversor en &aacute;rea com&uacute;n.</li></ul>");
        property.setType(propertyService.getTypeDao().find(PropertyType.BUILDING.name()));
        property.setCategory(propertyService.getCategoryDao().find(CategoryType.SELL.name()));
        property.setLocation(new Location(19.437243F, -70.6694F));
        property.setAttributes(Set.of(
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.ROOM.name()),
                        (short) 3),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.BATH.name()),
                        (short) 2),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.PARKING.name()),
                        (short) 2),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.AREA.name()),
                        (short) 127)));
        property.setFeatures(Set.of(new Feature(property, "Sala"), new Feature(property, "Balc??n"),
                new Feature(property, "Cocina con desayunador"), new Feature(property, "Comedor"),
                new Feature(property, "??rea de lavado"), new Feature(property, "Ba??o de servicio")));
        property.setMedias(Set.of(new Media(property, "1.jpg"), new Media(property, "2.jpeg"),
                        new Media(property, "3.jpg"), new Media(property, "4.jpg"), new Media(property, "5.jpg"),
                        new Media(property, "6.jpg")));
        propertyService.getPropertyDao().create(property);
    }

}
