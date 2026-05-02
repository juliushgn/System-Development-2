(function () {
  'use strict';

  /* ── Translations ─────────────────────────────────────────────── */
  var i18n = {
    de: {
      // Navigation
      'nav.label':          'Navigation',
      'nav.queue':          'Anfragen-Queue',
      'nav.dashboard':      'Dashboard',
      'nav.allRequests':    'Alle Anfragen',
      'nav.myRequests':     'Meine Anfragen',
      'nav.newRequest':     'Neue Anfrage',
      'nav.logout':         'Abmelden',
      'role.agent':         'Service Agent',
      'role.employee':      'Mitarbeiter',
      'role.manager':       'Manager',
      // Table headers
      'th.no':              'Ticket #',
      'th.title':           'Titel',
      'th.category':        'Kategorie',
      'th.priority':        'Priorität',
      'th.status':          'Status',
      'th.creator':         'Ersteller',
      'th.created':         'Erstellt',
      'th.assigned':        'Zugewiesen',
      'th.dept':            'Abteilung',
      // Agent queue
      'queue.title':        'Anfragen-Queue',
      'queue.subtitle':     'Offene und zugewiesene Anfragen verwalten',
      'queue.tabAll':       'Alle offen',
      'queue.tabMine':      'Mir zugewiesen',
      'queue.cardAll':      'Offene Anfragen',
      'queue.cardMine':     'Mir zugewiesene Anfragen',
      'queue.openLabel':    'offen',
      'queue.reqLabel':     'Anfragen',
      'queue.emptyAll':     'Keine offenen Anfragen. Alles erledigt!',
      'queue.emptyMine':    'Keine Anfragen sind Ihnen zugewiesen.',
      'queue.editBtn':      'Bearbeiten',
      // Agent detail
      'detail.back':        'Zurück',
      'detail.activity':    'Aktivität',
      'detail.noActivity':  'Noch keine Aktivität.',
      'detail.addComment':  'Kommentar hinzufügen',
      'detail.commentPh':   'Kommentar oder Hinweis…',
      'detail.internal':    'Interner Kommentar (nur für Agenten sichtbar)',
      'detail.resolution':  'Als Lösungshinweis markieren',
      'detail.postBtn':     'Posten',
      'detail.details':     'Details',
      'detail.creator':     'Ersteller',
      'detail.dept':        'Abteilung',
      'detail.category':    'Kategorie',
      'detail.assignedTo':  'Zugewiesen an',
      'detail.notAssigned': 'Nicht zugewiesen',
      'detail.created':     'Erstellt',
      'detail.modified':    'Zuletzt geändert',
      'detail.assign':      'Anfrage zuweisen',
      'detail.selectAgent': 'Agent wählen…',
      'detail.assignBtn':   'Zuweisen',
      'detail.reassign':    'Zuweisung ändern',
      'detail.status':      'Status aktualisieren',
      'detail.notePh':      'Optionaler Hinweis…',
      'detail.resTag':      'Lösungshinweis',
      'detail.intTag':      'Intern',
      // Employee – my requests
      'emp.title':          'Meine Anfragen',
      'emp.searchPh':       'Anfragen durchsuchen…',
      'emp.newBtn':         'Neue Anfrage',
      'emp.cardHeader':     'Anfragen',
      'emp.totalLabel':     'gesamt',
      'emp.empty':          'Sie haben noch keine Anfragen gestellt.',
      'emp.firstBtn':       'Erste Anfrage stellen',
      'emp.viewBtn':        'Ansehen',
      // Employee – new request
      'empNew.title':       'Neue Anfrage',
      'empNew.subtitle':    'Beschreiben Sie Ihr Anliegen',
      'empNew.labelTitle':  'Titel',
      'empNew.labelDesc':   'Beschreibung',
      'empNew.labelCat':    'Kategorie',
      'empNew.labelPrio':   'Priorität',
      'empNew.titlePh':     'Kurze, prägnante Beschreibung Ihrer Anfrage',
      'empNew.descPh':      'Beschreiben Sie Ihr Problem oder Anliegen ausführlich…',
      'empNew.catPh':       'Kategorie wählen…',
      'empNew.prioPh':      'Priorität wählen…',
      'empNew.submit':      'Anfrage absenden',
      'empNew.cancel':      'Abbrechen',
      // Employee – detail
      'empDet.activity':    'Aktivität',
      'empDet.noActivity':  'Noch keine Aktivität.',
      'empDet.details':     'Details',
      'empDet.category':    'Kategorie',
      'empDet.priority':    'Priorität',
      'empDet.assignedTo':  'Zugewiesen an',
      'empDet.notAssigned': 'Nicht zugewiesen',
      'empDet.created':     'Erstellt',
      'empDet.modified':    'Zuletzt geändert',
      // Manager dashboard
      'dash.title':         'Dashboard',
      'dash.subtitle':      'Übersicht über alle Serviceanfragen',
      'dash.total':         'Anfragen gesamt',
      'dash.open':          'Offen / In Bearbeitung',
      'dash.resolved':      'Gelöst',
      'dash.closed':        'Geschlossen',
      'dash.statusChart':   'Statusverteilung',
      'dash.prioChart':     'Prioritätsverteilung',
      'dash.catChart':      'Nach Kategorie',
      'dash.agentChart':    'Agenten-Auslastung',
      'dash.noAgent':       'Noch keine Zuweisung.',
      'dash.recent':        'Neueste Anfragen',
      'dash.viewAll':       'Alle ansehen',
      // Manager all requests
      'mgrReq.title':       'Alle Anfragen',
      'mgrReq.subtitle':    'Vollständige Übersicht aller Serviceanfragen',
      'mgrReq.results':     'Ergebnisse',
      'mgrReq.viewBtn':     'Ansehen',
      'mgrReq.empty':       'Keine Anfragen gefunden.',
      'mgrReq.searchPh':    'Anfragen durchsuchen…',
    },

    en: {
      // Navigation
      'nav.label':          'Navigation',
      'nav.queue':          'Request Queue',
      'nav.dashboard':      'Dashboard',
      'nav.allRequests':    'All Requests',
      'nav.myRequests':     'My Requests',
      'nav.newRequest':     'New Request',
      'nav.logout':         'Log out',
      'role.agent':         'Service Agent',
      'role.employee':      'Employee',
      'role.manager':       'Manager',
      // Table headers
      'th.no':              'Ticket #',
      'th.title':           'Title',
      'th.category':        'Category',
      'th.priority':        'Priority',
      'th.status':          'Status',
      'th.creator':         'Created by',
      'th.created':         'Created',
      'th.assigned':        'Assigned',
      'th.dept':            'Department',
      // Agent queue
      'queue.title':        'Request Queue',
      'queue.subtitle':     'Manage open and assigned requests',
      'queue.tabAll':       'All open',
      'queue.tabMine':      'Assigned to me',
      'queue.cardAll':      'Open Requests',
      'queue.cardMine':     'Requests assigned to me',
      'queue.openLabel':    'open',
      'queue.reqLabel':     'requests',
      'queue.emptyAll':     'No open requests. All done!',
      'queue.emptyMine':    'No requests are assigned to you.',
      'queue.editBtn':      'Edit',
      // Agent detail
      'detail.back':        'Back',
      'detail.activity':    'Activity',
      'detail.noActivity':  'No activity yet.',
      'detail.addComment':  'Add comment',
      'detail.commentPh':   'Comment or note…',
      'detail.internal':    'Internal comment (agents only)',
      'detail.resolution':  'Mark as resolution note',
      'detail.postBtn':     'Post',
      'detail.details':     'Details',
      'detail.creator':     'Created by',
      'detail.dept':        'Department',
      'detail.category':    'Category',
      'detail.assignedTo':  'Assigned to',
      'detail.notAssigned': 'Not assigned',
      'detail.created':     'Created',
      'detail.modified':    'Last modified',
      'detail.assign':      'Assign request',
      'detail.selectAgent': 'Select agent…',
      'detail.assignBtn':   'Assign',
      'detail.reassign':    'Change assignee',
      'detail.status':      'Update status',
      'detail.notePh':      'Optional note…',
      'detail.resTag':      'Resolution note',
      'detail.intTag':      'Internal',
      // Employee – my requests
      'emp.title':          'My Requests',
      'emp.searchPh':       'Search requests…',
      'emp.newBtn':         'New Request',
      'emp.cardHeader':     'Requests',
      'emp.totalLabel':     'total',
      'emp.empty':          'You haven\'t submitted any requests yet.',
      'emp.firstBtn':       'Submit first request',
      'emp.viewBtn':        'View',
      // Employee – new request
      'empNew.title':       'New Request',
      'empNew.subtitle':    'Describe your issue',
      'empNew.labelTitle':  'Title',
      'empNew.labelDesc':   'Description',
      'empNew.labelCat':    'Category',
      'empNew.labelPrio':   'Priority',
      'empNew.titlePh':     'Short, concise description',
      'empNew.descPh':      'Describe your problem or request in detail…',
      'empNew.catPh':       'Select category…',
      'empNew.prioPh':      'Select priority…',
      'empNew.submit':      'Submit request',
      'empNew.cancel':      'Cancel',
      // Employee – detail
      'empDet.activity':    'Activity',
      'empDet.noActivity':  'No activity yet.',
      'empDet.details':     'Details',
      'empDet.category':    'Category',
      'empDet.priority':    'Priority',
      'empDet.assignedTo':  'Assigned to',
      'empDet.notAssigned': 'Not assigned',
      'empDet.created':     'Created',
      'empDet.modified':    'Last modified',
      // Manager dashboard
      'dash.title':         'Dashboard',
      'dash.subtitle':      'Overview of all service requests',
      'dash.total':         'Total requests',
      'dash.open':          'Open / In progress',
      'dash.resolved':      'Resolved',
      'dash.closed':        'Closed',
      'dash.statusChart':   'Status distribution',
      'dash.prioChart':     'Priority distribution',
      'dash.catChart':      'By Category',
      'dash.agentChart':    'Agent workload',
      'dash.noAgent':       'No assignments yet.',
      'dash.recent':        'Recent requests',
      'dash.viewAll':       'View all',
      // Manager all requests
      'mgrReq.title':       'All Requests',
      'mgrReq.subtitle':    'Complete overview of all service requests',
      'mgrReq.results':     'results',
      'mgrReq.viewBtn':     'View',
      'mgrReq.empty':       'No requests found.',
      'mgrReq.searchPh':    'Search requests…',
    }
  };

  function applyLang(lang) {
    var t = i18n[lang] || i18n['de'];
    document.querySelectorAll('[data-i18n]').forEach(function (el) {
      var key = el.getAttribute('data-i18n');
      if (t[key] !== undefined) el.textContent = t[key];
    });
    document.querySelectorAll('[data-i18n-ph]').forEach(function (el) {
      var key = el.getAttribute('data-i18n-ph');
      if (t[key] !== undefined) el.placeholder = t[key];
    });
    localStorage.setItem('srm-lang', lang);
  }

  /* ── Dark mode ────────────────────────────────────────────────── */
  function applyTheme(theme) {
    document.documentElement.setAttribute('data-theme', theme);
    document.querySelectorAll('.srm-theme-btn').forEach(function (btn) {
      var icon  = btn.querySelector('i');
      var label = btn.querySelector('.pref-label');
      if (theme === 'dark') {
        if (icon)  icon.className = 'bi bi-sun';
        if (label) label.textContent = 'Hellmodus';
        btn.classList.add('active');
      } else {
        if (icon)  icon.className = 'bi bi-moon-stars';
        if (label) label.textContent = 'Dunkelmodus';
        btn.classList.remove('active');
      }
    });
    localStorage.setItem('srm-theme', theme);
  }

  function applyLangLabel(lang) {
    document.querySelectorAll('.srm-lang-btn').forEach(function (btn) {
      var label = btn.querySelector('.pref-label');
      if (label) label.textContent = lang === 'de' ? 'English' : 'Deutsch';
    });
  }

  var savedTheme = localStorage.getItem('srm-theme') || 'light';
  applyTheme(savedTheme);

  var savedLang = localStorage.getItem('srm-lang') || 'de';

  document.addEventListener('DOMContentLoaded', function () {
    applyLang(savedLang);
    applyLangLabel(savedLang);

    document.querySelectorAll('.srm-theme-btn').forEach(function (btn) {
      btn.addEventListener('click', function () {
        var current = document.documentElement.getAttribute('data-theme');
        applyTheme(current === 'dark' ? 'light' : 'dark');
      });
    });

    document.querySelectorAll('.srm-lang-btn').forEach(function (btn) {
      btn.addEventListener('click', function () {
        var current = localStorage.getItem('srm-lang') || 'de';
        var next = current === 'de' ? 'en' : 'de';
        applyLang(next);
        applyLangLabel(next);
      });
    });
  });

  /* ── Sidebar (mobile) ─────────────────────────────────────────── */
  var sidebar = document.getElementById('sidebar');
  var toggle  = document.getElementById('sidebarToggle');
  var overlay = document.getElementById('sidebarOverlay');

  function openSidebar() {
    if (sidebar) sidebar.classList.add('open');
    if (overlay) overlay.classList.add('show');
    document.body.style.overflow = 'hidden';
  }
  function closeSidebar() {
    if (sidebar) sidebar.classList.remove('open');
    if (overlay) overlay.classList.remove('show');
    document.body.style.overflow = '';
  }
  if (toggle)  toggle.addEventListener('click', openSidebar);
  if (overlay) overlay.addEventListener('click', closeSidebar);

  /* ── Tabs ─────────────────────────────────────────────────────── */
  document.querySelectorAll('[data-tab]').forEach(function (btn) {
    btn.addEventListener('click', function () {
      var group  = btn.dataset.tabGroup;
      var target = btn.dataset.tab;
      document.querySelectorAll('[data-tab-group="' + group + '"]')
        .forEach(function (t) { t.classList.remove('active'); });
      document.querySelectorAll('[data-panel-group="' + group + '"]')
        .forEach(function (p) { p.classList.remove('active'); });
      btn.classList.add('active');
      var panel = document.querySelector(
        '[data-panel-group="' + group + '"][data-panel="' + target + '"]');
      if (panel) panel.classList.add('active');
    });
  });

  /* ── Alert dismiss ────────────────────────────────────────────── */
  document.querySelectorAll('[data-dismiss="alert"]').forEach(function (btn) {
    btn.addEventListener('click', function () {
      var alert = btn.closest('.alert');
      if (alert) {
        alert.style.transition = 'opacity 150ms';
        alert.style.opacity = '0';
        setTimeout(function () { alert.remove(); }, 150);
      }
    });
  });

  /* ── Inline toggle (reassign form etc.) ───────────────────────── */
  document.querySelectorAll('[data-toggle-target]').forEach(function (btn) {
    btn.addEventListener('click', function () {
      var target = document.getElementById(btn.getAttribute('data-toggle-target'));
      if (!target) return;
      target.style.display = (target.style.display === 'none' || !target.style.display)
        ? 'block' : 'none';
    });
  });

})();
