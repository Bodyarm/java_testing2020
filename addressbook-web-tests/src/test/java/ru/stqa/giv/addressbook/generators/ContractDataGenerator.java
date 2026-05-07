package ru.stqa.giv.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.giv.addressbook.model.ContractData;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContractDataGenerator {

    @Parameter(names = "-c", description = "Contract count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;
    @Parameter(names = "-df", description = "Data format")
    public String format;


    public static void main(String[] args) throws IOException {

        ContractDataGenerator generator = new ContractDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContractData> contracts = generateContracts(count);
             if (format.equals("CSV"))  saveAsCSV(contracts,new File(file));
        else if (format.equals("XML"))  saveAsXML(contracts, new File(file));
        else if (format.equals("JSON")) saveAsJson(contracts, new File(file));
        else System.out.println("Unrecognized format "+ format);
    }

    private void saveAsJson(List<ContractData> contracts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contracts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsXML(List<ContractData> contracts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContractData.class);
        xStream.alias("contract", ContractData.class);
        String xml = xStream.toXML(contracts);
        //auto-close file by using try option
        try (Writer writer = new FileWriter(file);){
            writer.write(xml);
        }
    }

    private void saveAsCSV(List<ContractData> contracts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for(ContractData contract : contracts){
            writer.write(String.format("%s;%s;%s\n", contract.getLastname(), contract.getFirstname(),contract.getPostAddress()));
        }
        writer.close();
    }

    private List<ContractData> generateContracts(int count) {
        List<ContractData> contracts = new ArrayList<ContractData>();
        for (int i = 0; i< count; i++){
            contracts.add(new ContractData().withLastName(String.format("lastname %s",i))
                    .withFirstname(String.format("FirstName %s",i))
                    .withPostAddress(String.format("Bakers street %s",i))
            );
        }
        return contracts;

    }
}
