package com.example.dcmanager.service;

import com.example.dcmanager.model.Brand;
import com.example.dcmanager.model.DcItem;
import com.example.dcmanager.model.DistributionCentre;
import com.example.dcmanager.repository.DcItemRepository;
import com.example.dcmanager.repository.DistributionCentreRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DistributionService {
    private final DcItemRepository itemRepo;
    private final DistributionCentreRepository centreRepo;
    private static final double WAREHOUSE_LAT = 43.6532;
    private static final double WAREHOUSE_LON = -79.3832;

    public DistributionService(DcItemRepository itemRepo, DistributionCentreRepository centreRepo) {
        this.itemRepo = itemRepo;
        this.centreRepo = centreRepo;
    }

    public DcItem addItem(Long centreId, DcItem item) {
        DistributionCentre centre = centreRepo.findById(centreId).orElseThrow();
        item.setDistributionCentre(centre);
        return itemRepo.save(item);
    }

    public void deleteItem(Long centreId, Long itemId) {
        DcItem item = itemRepo.findById(itemId).orElseThrow();
        if (!item.getDistributionCentre().getId().equals(centreId)) throw new IllegalArgumentException();
        itemRepo.delete(item);
    }

    public List<DistributionCentre> listCentres() {
        return centreRepo.findAll();
    }

    public DcItem requestItem(String name, Brand brand) {
        List<DcItem> items = itemRepo.findByNameAndBrandAndQuantityGreaterThan(name, brand, 0);
        if (items.isEmpty()) throw new RuntimeException("Not available");
        DcItem best = items.stream()
                .min((i1, i2) -> Double.compare(
                        distance(i1.getDistributionCentre().getLatitude(), i1.getDistributionCentre().getLongitude(), WAREHOUSE_LAT, WAREHOUSE_LON),
                        distance(i2.getDistributionCentre().getLatitude(), i2.getDistributionCentre().getLongitude(), WAREHOUSE_LAT, WAREHOUSE_LON)
                )).get();
        best.setQuantity(best.getQuantity() - 1);
        return itemRepo.save(best);
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double dlat = Math.toRadians(lat2 - lat1);
        double dlon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dlat/2) * Math.sin(dlat/2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dlon/2) * Math.sin(dlon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c;
    }
}
