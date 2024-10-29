package com.bootcamp.demo.appConfig;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.Repository.TStockRepository;
import com.bootcamp.demo.entity.StockEntity;
import com.bootcamp.demo.infra.yahoo.CookieManager;
import com.bootcamp.demo.infra.yahoo.CookieManager1;
import com.bootcamp.demo.infra.yahoo.CrumbManager;
import com.bootcamp.demo.service.StockService;

@Component
public class AppStartRunner implements CommandLineRunner {
    @Autowired
    @Qualifier ("MyVer")
    private CookieManager1 cookieManager1;

    @Autowired
    private CrumbManager crumbManager;

    // @Autowired
    // @Qualifier("Vinver")
    // private CookieManager cookieManager;

    @Autowired
    private TStockRepository tStockRepository;

	@Override
	public void run(String... args) throws Exception {

        List<StockEntity> tStockEntities = List.of(
                new StockEntity("0388.HK"),
                new StockEntity("0700.HK"),
                new StockEntity("1357.HK"),
                new StockEntity("9990.HK"));

            tStockRepository.saveAll(tStockEntities);
        

   // String cookie = cookieManager1.getResponse();
       //String cookie = cookieManager.getCookieString();
       //System.out.println(cookie);

  //  String crumb = crumbManager.getCrumb();
    //    System.out.println(crumb);


}
}

    // @Override
    // public void run(String... args) throws Exception {
    //     List<StockDTO> data = this.stockService.getTable();
    //     List<TStockEntity> stockEntities = data.stream()//
    //     .map(sDto -> {
    //       return TStockEntity.builder()
    //         .id(sDto.getId())
    //         .symbol(sDto.getSymbol())
    //         .build();
    //     }).collect(Collectors.toList());
    // stockService.saveAll(TStockEntity);
    
