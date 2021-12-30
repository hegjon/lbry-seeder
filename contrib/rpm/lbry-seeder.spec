Name:    {{{ git_name name="lbry-seeder" }}}
Version: 0.0.1
Release: {{{ git_version }}}%{?dist}
Summary: LBRY seeder

License:    MIT
URL:        https://github.com/hegjon/lbry-seeder
VCS:        {{{ git_vcs }}}

Source:     {{{ git_pack }}}

BuildRequires: clojure
Requires(pre): shadow-utils

BuildRequires: systemd systemd-rpm-macros
%{?systemd_requires}

%description
%{summary}.

%prep
{{{ git_setup_macro }}}

%build
#nothing yet

%install
install -Dm 644 contrib/systemd/lbrynet.service %{buildroot}/%{_unitdir}/lbrynet.service
install -Dm 644 contrib/systemd/lbry-seeder.service %{buildroot}/%{_unitdir}/lbry-seeder.service
install -Dm 644 contrib/systemd/lbry-seeder.timer %{buildroot}/%{_unitdir}/lbry-seeder.timer

%pre
getent group lbry-seeder >/dev/null || groupadd -r lbry-seeder
getent passwd lbry-seeder >/dev/null || \
    useradd -r -g lbry-seeder -d %{_sharedstatedir}/lbry-seeder -s /sbin/nologin \
    -c "LBRY seeder" lbry-seeder
exit 0

%post
%systemd_post lbrynet.service
%systemd_post lbry-seeder.service
%systemd_post lbry-seeder.timer

%preun
%systemd_preun lbrynet.service
%systemd_preun lbry-seeder.service
%systemd_preun lbry-seeder.timer

%postun
%systemd_postun_with_restart lbrynet.service
%systemd_postun lbry-seeder.service #cron-job, no restart
%systemd_postun lbry-seeder.timer

%files
%doc README.md
%doc CHANGELOG.md
%license LICENSE
%{_unitdir}/lbrynet.service
%{_unitdir}/lbry-seeder.service
%{_unitdir}/lbry-seeder.timer

%changelog
{{{ git_changelog }}}
