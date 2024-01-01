package io.github.rawdiamondmc.get_bds;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        getLink("preview_linux_link", "https://minecraft.azureedge.net/bin-linux-preview/bedrock-server-[a-zA-Z0-9.-]+.zip");
        getLink("preview_win_link", "https://minecraft.azureedge.net/bin-win-preview/bedrock-server-[a-zA-Z0-9.-]+.zip");
        getLink("linux_link", "https://minecraft.azureedge.net/bin-linux/bedrock-server-[a-zA-Z0-9.-]+.zip");
        getLink("win_link", "https://minecraft.azureedge.net/bin-win/bedrock-server-[a-zA-Z0-9.-]+.zip");
    }

    public static void getLink(String outputFileName, String linkPattern) throws IOException {
        Document document = Jsoup.connect("https://www.minecraft.net/en-us/download/server/bedrock").get();
        Elements links = document.select("a[href]");
        FileWriter writer = new FileWriter(outputFileName);

        for (Element link : links) {
            String href = link.attr("href");
            if (href.matches(linkPattern)) {
                System.out.println("Found link: " + href);
                writer.write(href + "\n");
            }
        }

        writer.close();
    }
}