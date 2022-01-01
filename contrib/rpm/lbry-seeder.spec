Name:    {{{ git_name name="lbry-seeder" }}}
Version: 0.0.1
Release: {{{ git_version }}}%{?dist}
Summary: LBRY seeder

License: MIT
URL:     https://github.com/hegjon/lbry-seeder
VCS:     {{{ git_vcs }}}
Source:  {{{ git_pack }}}

BuildArch: noarch

Requires: java-headless
Requires: javapackages-tools
BuildRequires: javapackages-tools

BuildRequires: maven
Requires(pre): shadow-utils

BuildRequires: systemd systemd-rpm-macros
%{?systemd_requires}

%description
LBRY seeder contains script for downloading new content from channel defined \
by the user. Includes init script for running a dedicated lbrynet instance and \
cron jobs for checking new content.

%prep
{{{ git_setup_macro }}}

%build
mvn package
mvn dependency:copy-dependencies

%install
install -Dm 644 target/*.jar %{buildroot}%{_javadir}/%{name}/%{name}-%{version}.jar
install -Dm 644 target/dependency/*.jar %{buildroot}%{_javadir}/%{name}/
install -Dm 644 contrib/systemd/lbrynet.service %{buildroot}%{_unitdir}/lbrynet.service
install -Dm 644 contrib/systemd/lbry-seeder.service %{buildroot}%{_unitdir}/lbry-seeder.service
install -Dm 644 contrib/systemd/lbry-seeder.timer %{buildroot}%{_unitdir}/lbry-seeder.timer

# startup script
%jpackage_script lbry_seeder.core "" "" lbry-seeder %{name} false

install -Ddm 750 %{buildroot}%{_sharedstatedir}/lbry-seeder

install -Ddm 755 %{buildroot}%{_sysconfdir}/lbry-seeder
install -m 644 contrib/lbrynet.yml %{buildroot}%{_sysconfdir}/lbry-seeder/lbrynet.yml

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
#cronjob, no need to restart
%systemd_postun lbry-seeder.service
%systemd_postun lbry-seeder.timer

%files
%doc README.md
%doc CHANGELOG.md
%license LICENSE
%{_sysconfdir}/lbry-seeder
%{_bindir}/%{name}
%{_javadir}/%{name}
%{_unitdir}/lbrynet.service
%{_unitdir}/lbry-seeder.service
%{_unitdir}/lbry-seeder.timer
%dir %attr(750, lbry-seeder, lbry-seeder) %{_sharedstatedir}/lbry-seeder

%changelog
{{{ git_changelog }}}
