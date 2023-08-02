package com.codenfast.developersuniverse.browserbot.service;

import com.codenfast.developersuniverse.entitydto.download.DownloadIntentDto;
import com.codenfast.developersuniverse.entitydto.download.DownloadStatusDto;
import com.codenfast.developersuniverse.entitydto.game.weapon.WeaponDto;
import com.codenfast.developersuniverse.entitydto.media.InvoiceLicenceDto;
import com.codenfast.developersuniverse.entitydto.media.MediaDownloadSourceDto;
import com.codenfast.developersuniverse.entitydto.media.MediaDto;
import com.codenfast.developersuniverse.feignclients.MediaServiceFeignClient;
import com.codenfast.developersuniverse.model.CodenfastException;
import com.codenfast.developersuniverse.model.OpenGraph;
import com.codenfast.developersuniverse.model.RequestGrid;
import com.codenfast.developersuniverse.utils.StringConstant;
import com.codenfast.developersuniverse.utils.StringProcess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrowserBotServiceImpl implements BrowserBotService {

    private final MediaServiceFeignClient mediaServiceFeignClient;
    private DownloadIntentDto justMediaDownloadIntent;
    private DownloadIntentDto licenceMediaDownloadIntent;
    private DownloadIntentDto nonCopyrightMusicDownloadIntent;
    private DownloadStatusDto waitingDownloadStatusDto;
    private DownloadStatusDto doneDownloadStatusDto;

    private final static String PIXABAY_MUSIC = "https://pixabay.com";

    @Override
    public void getWeapons() {
        System.setProperty("webdriver.edge.driver", new File("browser-bot\\msedgedriver.exe").getAbsolutePath());
        try {
//            Runtime.getRuntime().exec("taskkill /im firefox.exe /f");
//            Runtime.getRuntime().exec("taskkill /im geckodriver.exe /f");
            Runtime.getRuntime().exec("taskkill /im msedge.exe /f");
            Runtime.getRuntime().exec("taskkill /im msedgedriver.exe /f");
            Thread.sleep(10000);
        } catch (Exception ignored) {

        }

        final String startPage = "https://scum.fandom.com/wiki/All_Weapons";

        EdgeOptions options = new EdgeOptions();
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.addArguments("--remote-allow-origins=*");
        options.setCapability("ignore-certificate-errors", true);
        WebDriver driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.of(15, ChronoUnit.SECONDS));
        driver.get(startPage);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//a[@title='TEC01 M9']")));
        List<WebElement> tables = driver.findElements(By.className("wikitable"));
        for(int i = 0; i < 12 ; i++) {
            WebElement table = tables.get(i);
            List<WebElement> rows = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
            for (WebElement row : rows) {
                String name = "";
                String link = "";
                WebElement nameColumn = row.findElements(By.tagName("td")).get(1);
                if(CollectionUtils.isNotEmpty(nameColumn.findElements(By.tagName("a")))) {
                    WebElement element = nameColumn.findElements(By.tagName("a")).get(0);
                    name = element.getAttribute("title");
                    link = element.getAttribute("href");
                } else {
                    name = nameColumn.getText();
                }
                if (StringUtils.isBlank(name) || StringUtils.isBlank(link)) {
                    continue;
                }
                log.info("name: {}, link: {}", name, link);
            }
        }
    }

    private void getWeaponInfo(WebDriver webDriver, String weaponUrl) {
        webDriver.get(weaponUrl);
        WebElement aside = webDriver.findElements(By.tagName("aside")).get(0);
        WeaponDto weapon = new WeaponDto();
        weapon.setName(webDriver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div/aside/h2")).getText());
//        weapon.setDescription(webDriver.findElements());
        weapon.setImage(webDriver.findElement(By.className("image-thumbnail")).getAttribute("href"));
//        weapon.setGridX();
//        weapon.setGridY();
//        weapon.setCode();
//        weapon.setCategory();
//        weapon.setWeaponAmmoList();
//        weapon.setAttachmentList();
    }

//    @Scheduled(fixedDelay = 604800000) // Weekly
    @Override
    public void pixabayMusic() {
//        HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_ESR);
//        driver.setJavascriptEnabled(true);
//        System.setProperty("webdriver.chrome.driver","E:\\workspace-spring\\developersuniverse\\browser-bot\\chromedriver.exe");
//        HashMap<String, Object> chromePrefs = new HashMap<>();
//        chromePrefs.put("profile.default_content_settings.popups", 0);
//        chromePrefs.put("download.default_directory", musicsFolder.getAbsolutePath());
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("prefs", chromePrefs);
//        ChromeDriver driver = new ChromeDriver(options);

        try {
//            Runtime.getRuntime().exec("taskkill /im firefox.exe /f");
//            Runtime.getRuntime().exec("taskkill /im geckodriver.exe /f");
            Runtime.getRuntime().exec("taskkill /im msedge.exe /f");
            Runtime.getRuntime().exec("taskkill /im msedgedriver.exe /f");
            Thread.sleep(10000);
        } catch (Exception ignored) {

        }

        System.setProperty("webdriver.gecko.driver", new File("browser-bot\\geckodriver.exe").getAbsolutePath());
        System.setProperty("webdriver.edge.driver", new File("browser-bot\\msedgedriver.exe").getAbsolutePath());

//        FirefoxProfile profile = new FirefoxProfile();
//        profile.setPreference("general.useragent.override", StringConstant.USER_AGENT);
//        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        firefoxOptions.setProfile(profile);
//        FirefoxDriver driverMain = new FirefoxDriver(firefoxOptions);
//        driverMain.manage().window().maximize();
//        driverMain.manage().deleteAllCookies();

        WebDriver driverMain = new EdgeDriver();
                driverMain.manage().window().maximize();
                driverMain.manage().deleteAllCookies();
        try {
            if (ObjectUtils.isEmpty(nonCopyrightMusicDownloadIntent)) {
                nonCopyrightMusicDownloadIntent = getDownloadIntent(StringConstant.NON_COPYRIGHT_MUSIC_DOWNLOAD);
            }
            if (ObjectUtils.isEmpty(justMediaDownloadIntent)) {
                justMediaDownloadIntent = getDownloadIntent(StringConstant.JUST_MEDIA_DOWNLOAD);
            }
            if (ObjectUtils.isEmpty(licenceMediaDownloadIntent)) {
                licenceMediaDownloadIntent = getDownloadIntent(StringConstant.LICENCE_MEDIA_DOWNLOAD);
            }
            if (ObjectUtils.isEmpty(waitingDownloadStatusDto)) {
                waitingDownloadStatusDto = getDownloadStatus(StringConstant.WAITING);
            }
            if (ObjectUtils.isEmpty(doneDownloadStatusDto)) {
                doneDownloadStatusDto = getDownloadStatus(StringConstant.DONE);
            }

            Thread.sleep(5000);

            WebDriverWait driverMainWait = new WebDriverWait(driverMain, Duration.of(15, ChronoUnit.SECONDS));
            driverMain.get(PIXABAY_MUSIC + "/music/search");
            driverMainWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div/div/div/main/div[2]/article[1]")));

            OpenGraph openGraph = BrowserUtils.getOpenGraph(driverMain);
            MediaDownloadSourceDto mediaDownloadSourceDto = openGraphToMediaDownloadSourceDto(openGraph);
            ResponseEntity<List<MediaDownloadSourceDto>> mediaDownloadSourceDtoResponseEntity = mediaServiceFeignClient.mediaDownloadSourceServiceGrid(RequestGrid.getByProperty(StringConstant.URL, mediaDownloadSourceDto.getUrl()));
            if (!HttpStatus.OK.equals(mediaDownloadSourceDtoResponseEntity.getStatusCode())) {
                throw new CodenfastException("There is something wrong about checking MediaDownloadSource by FeignClient");
            }
            if (CollectionUtils.isEmpty(mediaDownloadSourceDtoResponseEntity.getBody())) {
                mediaDownloadSourceDto = mediaServiceFeignClient.mediaDownloadSourceServiceSave(mediaDownloadSourceDto).getBody();
            } else {
                mediaDownloadSourceDto.setId(mediaDownloadSourceDtoResponseEntity.getBody().get(0).getId());
                mediaDownloadSourceDto.setImage(mediaDownloadSourceDtoResponseEntity.getBody().get(0).getImage());
                mediaDownloadSourceDto = mediaServiceFeignClient.mediaDownloadSourceServiceSave(mediaDownloadSourceDto).getBody();
            }
            try {
                if (mediaDownloadSourceDto != null && mediaDownloadSourceDto.getImage() == null) {
                    mediaDownloadSourceDto.setImage(urlToMedia(openGraph.getImage(), mediaDownloadSourceDto, "Logo of " + openGraph.getTitle()));
                    mediaDownloadSourceDto = mediaServiceFeignClient.mediaDownloadSourceServiceUpdate(mediaDownloadSourceDto).getBody();
                }
            } catch (Exception ignored) {
              // Sometimes there is a download blocker
                // TODO: Image download and send via FeignClient to download
            }

            pixabayOpenFilter(driverMain, driverMainWait);
            Map<String, String> typeUrl = new HashMap<>();
            for (WebElement genreLink : driverMain.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/nav/div/div[2]/div[2]")).findElements(By.tagName("a"))) {
                typeUrl.put(genreLink.getAttribute("data-value"), StringProcess.linkAddContextUrl(PIXABAY_MUSIC, genreLink.getAttribute("href")));
            }
            // Click Show More
            driverMain.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/nav/div/div[2]/div[2]/div[2]/button"));
            for (WebElement genreLink : driverMain.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/nav/div/div[2]/div[2]/div[2]/button")).findElements(By.tagName("a"))) {
                typeUrl.put(genreLink.getAttribute("data-value"), StringProcess.linkAddContextUrl(PIXABAY_MUSIC, genreLink.getAttribute("href")));
            }

            driverMain.quit();

            ExecutorService executorService = Executors.newFixedThreadPool(6);
            CompletableFuture<?>[] genreFuture = new CompletableFuture[typeUrl.size()];
            Iterator<Map.Entry<String, String>> mapEntrySetIterator = typeUrl.entrySet().iterator();
            for (int index = 0; index < typeUrl.entrySet().size(); index++) {
                Map.Entry<String, String> entry = mapEntrySetIterator.next();
                genreFuture[index] = getGenreMusics(entry, mediaDownloadSourceDto, executorService);
            }
            CompletableFuture.allOf(genreFuture).join();
            executorService.shutdownNow();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        } finally {

        }
    }

    @Async
    CompletableFuture<Void> getGenreMusics(Map.Entry<String, String> entry, MediaDownloadSourceDto finalMediaDownloadSourceDto, ExecutorService executorService) {
        return CompletableFuture.runAsync(() -> {
//            FirefoxProfile profile = new FirefoxProfile();
//            profile.setPreference("general.useragent.override", StringConstant.USER_AGENT);
//            FirefoxOptions firefoxOptions = new FirefoxOptions();
//            firefoxOptions.setProfile(profile);
//            FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
            WebDriver driver = new EdgeDriver();
            try {

                WebDriverWait driverWait = new WebDriverWait(driver, Duration.of(30, ChronoUnit.SECONDS));
                driver.manage().window().setSize(new Dimension(1366,768));
                driver.manage().deleteAllCookies();
                driver.get(entry.getValue());

                driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div/div/main/div[2]")));

            } catch (Exception e) {
                log.error(e.getMessage(),e);
                throw new CompletionException(e.getMessage(), e);
            } finally {
                driver.quit();
            }
        }, executorService);
    }

    private void generatePixabayLicence(MediaDto mediaDto, String trackId) throws IOException {
        String artistName = mediaDto.getArtist();
        String attributionLink = mediaDto.getAttributionLink().replace("/?tab=audio", "");
        String title = mediaDto.getName();
        String attributionSourceLink = mediaDto.getAttributionSourceLink();
        String generatedIndex = generatePixabayLicenceFile(attributionLink, title, attributionSourceLink, trackId);

        InvoiceLicenceDto dto = new InvoiceLicenceDto();
        dto.setPassive(Boolean.FALSE);
        dto.setCreateTime(LocalDateTime.now());
        dto.setUpdateTime(LocalDateTime.now());
        dto.setName(title);
        dto.setDownloadedUrl(attributionLink);
        dto.setDescription(artistName + "-" + trackId + "-" + title + ".txt");
        dto.setMimeType("text/plain");
        dto.setSize((long) generatedIndex.getBytes(StandardCharsets.UTF_8).length);
        dto.setMedia(mediaDto);
        dto.setPartialDownloadSupport(Boolean.FALSE);
        dto.setDownloadIntent(licenceMediaDownloadIntent);
        dto.setFileBase64(Base64.getEncoder().encodeToString(generatedIndex.getBytes(StandardCharsets.UTF_8)));

        String issuer = "Pixabay";
        if (mediaDto.getMediaDownloadSource() != null) {
            issuer = StringUtils.isNotBlank(mediaDto.getMediaDownloadSource().getName()) ?
                    mediaDto.getMediaDownloadSource().getName() :
                    StringUtils.isNotBlank(mediaDto.getMediaDownloadSource().getTitle()) ?
                            mediaDto.getMediaDownloadSource().getTitle() : issuer;
        }
        dto.setIssuer(issuer);

        mediaServiceFeignClient.invoiceLicenceServiceSave(dto);
    }

    private String generatePixabayLicenceFile(String attributionLink, String title, String attributionSourceLink, String trackId) {
        return new StringBuilder()
                .append("PIXABAY LICENSE CERTIFICATE").append(System.lineSeparator())
                .append("==============================================").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("This document confirms the download of an audio file pursuant to the Pixabay License as defined in the Pixabay Terms of Service available at https://https://pixabay.com/service/terms/").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Licensor's Username:").append(System.lineSeparator())
                .append(attributionLink).append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Licensee:").append(System.lineSeparator())
                .append("codenfast").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Audio File Title:").append(System.lineSeparator())
                .append(title).append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Audio File URL:").append(System.lineSeparator())
                .append(attributionSourceLink).append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Audio File ID:").append(System.lineSeparator())
                .append(trackId).append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Date of download:").append(System.lineSeparator())
                .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(OffsetDateTime.now(ZoneOffset.UTC))).append(" UTC").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Pixabay GmbH c/o Lacore Rechtsanwälte LLP").append(System.lineSeparator())
                .append("Berliner Freiheit 2, 10785 Berlin, Germany").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Pixabay is a user-contributed stock content website. The above-named Licensor is responsible for this audio file. Pixabay monitors uploaded audio files only to a reasonable extent. Pixabay cannot be held responsible for the acts or omissions of its users and does not represent or warrant that any required third-party consents or licenses have been obtained.").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("For any queries related to this document please contact Pixabay via info@pixabay.com.").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("==== THIS IS NOT A TAX RECEIPT OR INVOICE ====")
                .toString();
    }
//
//    private void checkAndSaveMediaGenre(GenreDto genreDto, MediaDto mediaDto) throws CodenfastException {
//        RequestGrid requestGrid = new RequestGrid();
//        requestGrid.setPage(0);
//        requestGrid.setPageSize(1);
//        requestGrid.setPropertyList(Collections.singletonList("id"));
//        List<FilterParam> mediaGenreFilterList = new java.util.ArrayList<>();
//        Map<String, Object> mediaFilter = new HashMap<>();
//        mediaFilter.put(StringConstant.ID, mediaDto.getId());
//        mediaGenreFilterList.add(new FilterParam("media", StringConstant.EQUAL, mediaFilter));
//        requestGrid.setFilters(mediaGenreFilterList);
//        ResponseEntity<List<MediaGenreDto>> mediaGenreDtoListResponseEntity = mediaServiceFeignClient.mediaGenreServiceGrid(requestGrid);
//        if (!HttpStatus.OK.equals(mediaGenreDtoListResponseEntity.getStatusCode())) {
//            throw new CodenfastException("There is something wrong about checking MediaGenre by FeignClient");
//        }
//
//        if (CollectionUtils.isEmpty(mediaGenreDtoListResponseEntity.getBody())) {
//            MediaGenreDto mediaGenreDto = new MediaGenreDto();
//            mediaGenreDto.setMedia(mediaDto);
//            mediaGenreDto.setGenre(genreDto);
//            mediaServiceFeignClient.mediaGenreServiceSave(mediaGenreDto);
//        }
//    }

    private void pixabayOpenFilter(WebDriver driver, WebDriverWait driverMainWait) {
        if (CollectionUtils.isNotEmpty(driver.findElements(By.xpath("/html/body/div[1]/div[2]/div/div/div/main/div[1]/button")))
                && driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/main/div[1]/button")).isDisplayed()) {
            BrowserUtils.scrollToWebElement(driver, driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/main/div[1]/button")));
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/main/div[1]/button")).click();
        }
    }


    private MediaDto urlToMedia(String url, MediaDownloadSourceDto mediaDownloadSourceDto, String name) throws CodenfastException {

        ResponseEntity<List<MediaDto>> mediaDtoListResponseEntity = mediaServiceFeignClient.mediaServiceGrid(RequestGrid.getByProperty("downloadedUrl", url));
        if (!HttpStatus.OK.equals(mediaDtoListResponseEntity.getStatusCode())) {
            throw new CodenfastException("There is something wrong about checking Media by FeignClient");
        }
        if (CollectionUtils.isNotEmpty(mediaDtoListResponseEntity.getBody())) {
            return mediaDtoListResponseEntity.getBody().get(0);
        } else {
            MediaDto mediaDto = new MediaDto();
            mediaDto.setDownloadIntent(justMediaDownloadIntent);
            mediaDto.setDownloadedUrl(url);
            mediaDto.setName(name);
            mediaDto.setStatus(waitingDownloadStatusDto);
            ResponseEntity<MediaDto> mediaDtoResponseEntity = mediaServiceFeignClient.downloadManagerGenerateMediaFromUrl(mediaDto);
            if (!HttpStatus.OK.equals(mediaDtoResponseEntity.getStatusCode()) || ObjectUtils.isEmpty(mediaDtoResponseEntity.getBody())) {
                throw new CodenfastException("There is something wrong about creating a DownloadProcess by FeignClient");
            }
            mediaDto = mediaDtoResponseEntity.getBody();
            mediaDto.setDownloadIntent(justMediaDownloadIntent);
            mediaDto.setStatus(waitingDownloadStatusDto);
            mediaDto.setMediaDownloadSource(mediaDownloadSourceDto);
            ResponseEntity<MediaDto> imageMediaDtoResponseEntity = mediaServiceFeignClient.downloadManagerDownloadMedia(mediaDto);
            if (!HttpStatus.OK.equals(mediaDtoResponseEntity.getStatusCode())) {
                throw new CodenfastException("There is something wrong about checking Media by FeignClient");
            }
            return imageMediaDtoResponseEntity.getBody();
        }
    }

    /*
        private DownloadIntentDto justMediaDownloadIntent;
        private DownloadIntentDto nonCopyrightMusicDownloadIntent;
    */

    private DownloadIntentDto getDownloadIntent(String name) throws CodenfastException {
        RequestGrid requestGrid = RequestGrid.getByProperty(StringConstant.NAME, name);
        ResponseEntity<List<DownloadIntentDto>> responseEntityDownloadIntentList = mediaServiceFeignClient.downloadIntentServiceGrid(requestGrid);
        if (responseEntityDownloadIntentList != null && CollectionUtils.isEmpty(responseEntityDownloadIntentList.getBody())) {
            DownloadIntentDto downloadIntentDto = new DownloadIntentDto();
            downloadIntentDto.setName(name);
            downloadIntentDto.setPassive(Boolean.FALSE);
            ResponseEntity<DownloadIntentDto> responseEntityDownloadIntent = mediaServiceFeignClient.downloadIntentServiceSave(downloadIntentDto);
            if (responseEntityDownloadIntent != null && responseEntityDownloadIntent.getBody() != null) {
                return responseEntityDownloadIntent.getBody();
            }
            throw new CodenfastException("There is something wrong about checking DownloadIntent by FeignClient");
        }
        return (responseEntityDownloadIntentList != null &&
                CollectionUtils.isNotEmpty(responseEntityDownloadIntentList.getBody())) ?
                responseEntityDownloadIntentList.getBody().get(0) : null;
    }

    private DownloadStatusDto getDownloadStatus(String name) throws CodenfastException {
        RequestGrid requestGrid = RequestGrid.getByProperty(StringConstant.NAME, name);
        ResponseEntity<List<DownloadStatusDto>> responseEntityDownloadStatusList = mediaServiceFeignClient.downloadStatusServiceGrid(requestGrid);
        if (responseEntityDownloadStatusList != null && CollectionUtils.isEmpty(responseEntityDownloadStatusList.getBody())) {
            DownloadStatusDto downloadStatusDto = new DownloadStatusDto();
            downloadStatusDto.setName(name);
            downloadStatusDto.setPassive(Boolean.FALSE);
            ResponseEntity<DownloadStatusDto> responseEntityDownloadStatus = mediaServiceFeignClient.downloadStatusServiceSave(downloadStatusDto);
            if (responseEntityDownloadStatus != null && responseEntityDownloadStatus.getBody() != null) {
                return responseEntityDownloadStatus.getBody();
            }
            throw new CodenfastException("There is something wrong about checking DownloadStatus by FeignClient");
        }
        return (responseEntityDownloadStatusList != null &&
                CollectionUtils.isNotEmpty(responseEntityDownloadStatusList.getBody())) ?
                responseEntityDownloadStatusList.getBody().get(0) : null;
    }

    public MediaDownloadSourceDto openGraphToMediaDownloadSourceDto(OpenGraph openGraph) {
        MediaDownloadSourceDto result = new MediaDownloadSourceDto();
        result.setDescription(openGraph.getDescription());
        result.setTitle(openGraph.getTitle());
//        result.setImage(urlToMedia(openGraph.getImage(), finalMediaDownloadSourceDto, "Logo of " + result.getTitle()));
        result.setSiteName(openGraph.getSiteName());
        result.setUrl(openGraph.getUrl());
        result.setType(openGraph.getType());
        return result;
    }
}
