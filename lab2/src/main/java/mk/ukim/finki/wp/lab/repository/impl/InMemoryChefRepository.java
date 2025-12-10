//package mk.ukim.finki.wp.lab.repository.impl;
//
//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab.model.Chef;
//import mk.ukim.finki.wp.lab.repository.ChefRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//@Repository
//public class InMemoryChefRepository implements ChefRepository {
//
//    @Override
//    public List<Chef> findAll() {
//        return DataHolder.chefs;
//    }
//
//    @Override
//    public Optional<Chef> findById(Long id) {
//        return DataHolder.chefs.stream()
//                .filter(c -> c.getId().equals(id))
//                .findFirst();
//    }
//
//    @Override
//    public Chef save(Chef chef) {
//        // ако постои -> замени на исто место за да не се менува редоследот
//        for (int i = 0; i < DataHolder.chefs.size(); i++) {
//            if (DataHolder.chefs.get(i).getId().equals(chef.getId())) {
//                DataHolder.chefs.set(i, chef);
//                return chef;
//            }
//        }
//        // нов chef → додај на крај
//        DataHolder.chefs.add(chef);
//        return chef;
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        DataHolder.chefs.removeIf(c -> c.getId().equals(id));
//    }
//}
