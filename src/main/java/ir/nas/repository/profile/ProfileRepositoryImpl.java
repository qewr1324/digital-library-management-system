package ir.nas.repository.profile;

import java.util.Optional;

import ir.nas.model.Profile;
import ir.nas.util.HibernateUtil;

public class ProfileRepositoryImpl extends ProfileRepository
{
    @Override
    public Optional<Profile> findByWebsite(final String website)
    {
        final String FIND_BY_WEBSITE_QUERY = "FROM Profile b WHERE b.website = :profile_website";
        return Optional.ofNullable(HibernateUtil.transaction(em -> {
            return em.createQuery(FIND_BY_WEBSITE_QUERY, Profile.class)
                    .setParameter("profile_website", website)
                    .getSingleResult();
        }));
    }

    @Override
    protected Profile updateModel(final Profile src, final Profile target)
    {
        src.setBio(target.getBio());
        src.setWebsite(target.getWebsite());

        src.setAuthor(target.getAuthor());
        
        src.setCreatedAt(target.getCreatedAt());
        src.setUpdateAt(target.getUpdateAt());

        return src;
    }
}
