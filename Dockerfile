# burada ilk başta base imajımızı çekiyoruz java 17 olan

FROM maven:3.9.6-eclipse-temurin-17 AS build

# çalışma dizinini belirtiyorsun
WORKDIR /app
# şimdi app klasöründe konumlandın boşluğun ilk kısmı kendi lokalimzide ki dosyalar diğeri container a gidecek olan
COPY pom.xml .
#burada yine src klasörünü bulunduğumuz docker dizininde src klasörü olarak kayıt ediyoruz.
COPY src ./src
#burada -B batch mode(toplu mod) interaktif olmaması için, hiç soru sorma hep varsayılanı kullan diyoruz -DskipTests ise tesleri derle ama çalıştırma demek böylece daha hızlı paketleme yapılır
RUN mvn -B -DskipTests clean package

# burası final image kısmı yani finalde oluşturulacak image bir önceki adımda buil ettiğimiz kısmı referans alıcaz elimize jar olduğu için boyut açısından jdk değil jre ile devam etmek mantıklı
FROM eclipse-temurin:17-jre
#tekrardan içeride app klasöründe konumlanıyoruz
WORKDIR /app
#bir önceki aşamadan çıkan jar dosyasını referans göstererek bunu app.jar olarak app dizinine kopyalıyoruz
COPY --from=build /app/target/*.jar app.jar
#burayı vermezse varsayılan olarak zaten 8080 portundan başlayacak ama biz belirtmek istyoruz, hangi portta çalışacağına application.preporties kısmında veririrz
EXPOSE 8080
#burada artık en son aşamada çalıştırılmasını istediğimiz komutu çalıştırıyoruz java programı ve jar dosyası ve dosyanın olduğu dizin
ENTRYPOINT ["java","-jar","/app/app.jar"]