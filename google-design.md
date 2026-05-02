# Google Design System
**Brand**: Google LLC  
**Version**: 1.0  
**Last Updated**: May 2026  
**Framework**: Material Design 3 (Material You)

---

## 🎨 Brand Identity

Google's visual language is rooted in **clarity**, **approachability**, and **systematic color**. The design system balances playful energy with functional precision — always putting the user first. Every element is built to scale across billions of devices, from a Pixel watch to a 4K TV.

> **Core Principle**: Be helpful, accessible, and delightful — in that order.

---

## 🌈 Color System

Google uses a **four-color primary palette** derived from its iconic logo. All tokens support both light and dark surfaces.

### Brand Colors

| Name | Hex | Usage |
|------|-----|-------|
| Google Blue | `#4285F4` | Primary actions, links, focus rings |
| Google Red | `#EA4335` | Errors, destructive actions, alerts |
| Google Yellow | `#FBBC05` | Warnings, highlights, accent |
| Google Green | `#34A853` | Success, confirmations, availability |

---

### 🌞 Light Mode Tokens

```
Background
  --color-bg-base:        #FFFFFF   /* Page background */
  --color-bg-surface:     #F8F9FA   /* Cards, panels, sidebars */
  --color-bg-elevated:    #FFFFFF   /* Modals, dropdowns (with shadow) */
  --color-bg-overlay:     #00000014 /* Scrim / modal backdrop */

Text
  --color-text-primary:   #202124   /* Headings, body copy */
  --color-text-secondary: #5F6368   /* Captions, metadata, placeholders */
  --color-text-disabled:  #BDC1C6   /* Disabled labels */
  --color-text-inverse:   #FFFFFF   /* Text on colored backgrounds */
  --color-text-link:      #1A73E8   /* Hyperlinks */

Brand
  --color-brand-blue:     #1A73E8   /* Primary CTA (lightened for contrast) */
  --color-brand-red:      #D93025   /* Error / destructive */
  --color-brand-yellow:   #F9AB00   /* Warning */
  --color-brand-green:    #1E8E3E   /* Success */

Border & Dividers
  --color-border-default: #DADCE0   /* Standard dividers, input borders */
  --color-border-focus:   #1A73E8   /* Focused input rings */
  --color-border-error:   #D93025   /* Validation errors */

Interactive States
  --color-state-hover:    #1A73E80A /* Hover ripple (blue tint) */
  --color-state-pressed:  #1A73E814 /* Pressed state */
  --color-state-selected: #E8F0FE   /* Selected row / chip */
  --color-state-disabled: #F1F3F4   /* Disabled surface */
```

---

### 🌙 Dark Mode Tokens

```
Background
  --color-bg-base:        #202124   /* Page background */
  --color-bg-surface:     #292A2D   /* Cards, panels, sidebars */
  --color-bg-elevated:    #35363A   /* Modals, dropdowns */
  --color-bg-overlay:     #00000066 /* Scrim / modal backdrop */

Text
  --color-text-primary:   #E8EAED   /* Headings, body copy */
  --color-text-secondary: #9AA0A6   /* Captions, metadata, placeholders */
  --color-text-disabled:  #5F6368   /* Disabled labels */
  --color-text-inverse:   #202124   /* Text on light surfaces */
  --color-text-link:      #8AB4F8   /* Hyperlinks (lightened for dark bg) */

Brand
  --color-brand-blue:     #8AB4F8   /* Primary CTA */
  --color-brand-red:      #F28B82   /* Error / destructive */
  --color-brand-yellow:   #FDD663   /* Warning */
  --color-brand-green:    #81C995   /* Success */

Border & Dividers
  --color-border-default: #3C4043   /* Standard dividers, input borders */
  --color-border-focus:   #8AB4F8   /* Focused input rings */
  --color-border-error:   #F28B82   /* Validation errors */

Interactive States
  --color-state-hover:    #8AB4F814 /* Hover ripple */
  --color-state-pressed:  #8AB4F824 /* Pressed state */
  --color-state-selected: #394457   /* Selected row / chip */
  --color-state-disabled: #2D2E31   /* Disabled surface */
```

---

## 🔤 Typography

Google uses **Google Sans** for UI and marketing, paired with **Roboto** for dense information displays.

### Typefaces

| Role | Font Family | Notes |
|------|-------------|-------|
| Display / Headings | `Google Sans Display` | Rounded, expressive — used for hero text |
| UI / Body | `Google Sans` | Default for most interface text |
| Dense / Data | `Roboto` | Tables, code adjacent, metadata |
| Monospace | `Roboto Mono` | Code, search queries, terminal |

### Type Scale

| Token | Size | Weight | Line Height | Usage |
|-------|------|--------|-------------|-------|
| `--text-display-lg` | 57px | 400 | 64px | Hero banners |
| `--text-display-md` | 45px | 400 | 52px | Feature headers |
| `--text-display-sm` | 36px | 400 | 44px | Section titles |
| `--text-headline-lg` | 32px | 400 | 40px | Page headings |
| `--text-headline-md` | 28px | 400 | 36px | Card headings |
| `--text-headline-sm` | 24px | 400 | 32px | Modal titles |
| `--text-title-lg` | 22px | 500 | 28px | Subheadings |
| `--text-title-md` | 16px | 500 | 24px | List titles |
| `--text-title-sm` | 14px | 500 | 20px | Dense list titles |
| `--text-body-lg` | 16px | 400 | 24px | Default body text |
| `--text-body-md` | 14px | 400 | 20px | Secondary body |
| `--text-body-sm` | 12px | 400 | 16px | Captions, helpers |
| `--text-label-lg` | 14px | 500 | 20px | Button text |
| `--text-label-md` | 12px | 500 | 16px | Chip labels |
| `--text-label-sm` | 11px | 500 | 16px | Badge text |

---

## 📐 Spacing & Layout

Google uses an **8px base grid** with a 4px fine grid for micro-spacing.

### Spacing Scale

```
--space-1:   4px    /* Icon padding, tight gaps */
--space-2:   8px    /* Default element gap */
--space-3:   12px   /* Compact component padding */
--space-4:   16px   /* Standard padding */
--space-5:   20px   /* Medium spacing */
--space-6:   24px   /* Section padding (mobile) */
--space-8:   32px   /* Card padding, section gap */
--space-10:  40px   /* Large section gap */
--space-12:  48px   /* Hero padding */
--space-16:  64px   /* Desktop section gap */
--space-20:  80px   /* Major section breaks */
--space-24:  96px   /* Full-width section padding */
```

### Layout Grid

| Breakpoint | Columns | Gutter | Margin |
|-----------|---------|--------|--------|
| Mobile `< 600px` | 4 | 16px | 16px |
| Tablet `600–904px` | 8 | 24px | 32px |
| Desktop `905–1239px` | 12 | 24px | 32px |
| Wide `≥ 1240px` | 12 | 24px | Auto |
| Max content width | — | — | 1440px |

---

## 🔘 Border Radius

```
--radius-none:   0px
--radius-xs:     4px    /* Chips, tags, small badges */
--radius-sm:     8px    /* Buttons, inputs */
--radius-md:     12px   /* Cards, dialogs */
--radius-lg:     16px   /* Bottom sheets, large cards */
--radius-xl:     28px   /* Floating action buttons */
--radius-full:   9999px /* Pills, avatars, toggles */
```

---

## 🌑 Elevation & Shadow

Material You uses **tonal elevation** (color overlay) in dark mode, not just drop shadows.

### Light Mode Shadows

```
--shadow-0: none
--shadow-1: 0 1px 2px rgba(0,0,0,0.10), 0 1px 3px 1px rgba(0,0,0,0.06)
--shadow-2: 0 1px 2px rgba(0,0,0,0.10), 0 2px 6px 2px rgba(0,0,0,0.08)
--shadow-3: 0 4px 8px 3px rgba(0,0,0,0.10), 0 1px 3px rgba(0,0,0,0.12)
--shadow-4: 0 6px 10px 4px rgba(0,0,0,0.10), 0 2px 3px rgba(0,0,0,0.12)
--shadow-5: 0 8px 12px 6px rgba(0,0,0,0.10), 0 4px 4px rgba(0,0,0,0.12)
```

### Dark Mode Tonal Overlay

In dark mode, surfaces gain a blue tint based on elevation level:

| Level | Overlay Opacity | Use Case |
|-------|----------------|----------|
| 0 | 0% | Base surface |
| 1 | 5% | Cards |
| 2 | 8% | Contained buttons hover |
| 3 | 11% | Navigation drawer |
| 4 | 12% | App bar |
| 5 | 14% | FAB, dialogs |

---

## 🧩 Core Components

### Button

| Variant | Light BG | Light Text | Dark BG | Dark Text | Use |
|---------|----------|-----------|---------|-----------|-----|
| Filled | `#1A73E8` | `#FFFFFF` | `#8AB4F8` | `#002884` | Primary CTA |
| Tonal | `#E8F0FE` | `#1A73E8` | `#394457` | `#8AB4F8` | Secondary CTA |
| Outlined | `transparent` | `#1A73E8` | `transparent` | `#8AB4F8` | Tertiary |
| Text | `transparent` | `#1A73E8` | `transparent` | `#8AB4F8` | Inline action |
| Elevated | `#FFFFFF` | `#1A73E8` | `#35363A` | `#8AB4F8` | Floating actions |

Button specs: `height: 40px`, `padding: 0 24px`, `border-radius: var(--radius-sm)`, `font: var(--text-label-lg)`

---

### Input Field

**Light mode:**
- Background: `#FFFFFF`
- Border: `1px solid #DADCE0`
- Focus border: `2px solid #1A73E8`
- Label (float): `#5F6368` → `#1A73E8` on focus
- Error border: `#D93025`

**Dark mode:**
- Background: `#292A2D`
- Border: `1px solid #3C4043`
- Focus border: `2px solid #8AB4F8`
- Label (float): `#9AA0A6` → `#8AB4F8` on focus
- Error border: `#F28B82`

Specs: `height: 56px`, `padding: 16px`, `border-radius: var(--radius-xs) var(--radius-xs) 0 0` (filled) or `var(--radius-sm)` (outlined)

---

### Navigation Bar (Bottom)

| State | Light | Dark |
|-------|-------|------|
| Background | `#FFFFFF` | `#292A2D` |
| Active indicator | `#E8F0FE` | `#394457` |
| Active icon | `#1A73E8` | `#8AB4F8` |
| Inactive icon | `#5F6368` | `#9AA0A6` |

Height: `80px` | Icon size: `24px` | Label: `var(--text-label-md)`

---

### Card

| Property | Light | Dark |
|----------|-------|------|
| Background | `#FFFFFF` | `#292A2D` |
| Border | `1px solid #DADCE0` | `1px solid #3C4043` |
| Hover shadow | `--shadow-2` | tonal overlay `8%` |
| Border radius | `var(--radius-md)` | `var(--radius-md)` |
| Padding | `var(--space-4)` | `var(--space-4)` |

---

### Chip

**Filter / Input / Assist / Suggestion variants**

| State | Light | Dark |
|-------|-------|------|
| Default | Border `#79747E`, text `#1C1B1F` | Border `#938F99`, text `#E6E1E5` |
| Selected | BG `#E8F0FE`, text `#1A73E8` | BG `#394457`, text `#8AB4F8` |

Specs: `height: 32px`, `padding: 0 16px`, `border-radius: var(--radius-full)`

---

## 🎭 Motion & Animation

Google motion is expressive and purposeful, using Material's **standard easing**.

### Easing Curves

```css
--easing-standard:         cubic-bezier(0.2, 0, 0, 1.0)    /* Most transitions */
--easing-standard-decel:   cubic-bezier(0, 0, 0, 1.0)      /* Elements entering */
--easing-standard-accel:   cubic-bezier(0.3, 0, 1, 1)      /* Elements exiting */
--easing-emphasized:       cubic-bezier(0.2, 0, 0, 1.0)    /* Large spatial motion */
--easing-spring:           cubic-bezier(0.175, 0.885, 0.32, 1.275) /* Bounce/delight */
```

### Duration Tokens

```
--duration-50:    50ms   /* Micro: state changes (checkbox, toggle) */
--duration-100:   100ms  /* Short 1: fade in/out */
--duration-200:   200ms  /* Short 2: button hover, chip */
--duration-300:   300ms  /* Medium 1: cards expanding */
--duration-400:   400ms  /* Medium 2: bottom sheets, dialogs */
--duration-500:   500ms  /* Long 1: page transitions */
--duration-700:   700ms  /* Long 2: full-screen takeovers */
```

### Key Interaction Patterns

- **Ripple**: Radial expand from touch point, `200ms`, `var(--easing-standard)`
- **FAB → Dialog expand**: Container transform, `400ms`, `var(--easing-emphasized)`
- **Page enter**: Fade + slide up `16px`, `300ms`, `var(--easing-standard-decel)`
- **Sheet enter**: Slide up from bottom, `400ms`, `var(--easing-emphasized)`
- **Tooltip**: Fade in `100ms`, fade out `50ms`

---

## ♿ Accessibility

| Standard | Requirement |
|----------|-------------|
| Contrast (body text) | Minimum **4.5:1** (AA) |
| Contrast (large text) | Minimum **3:1** (AA) |
| Contrast (UI components) | Minimum **3:1** (AA) |
| Focus ring | `2px solid` brand color + `2px offset` |
| Touch target | Minimum **48×48px** |
| Motion | Respect `prefers-reduced-motion` |
| Color alone | Never convey info by color only |

All interactive elements must have visible focus indicators. Use `aria-label` for icon-only buttons.

---

## 📦 Icon System

**Library**: Google Material Symbols  
**Style variants**: Outlined (default), Filled, Rounded, Sharp, Two-tone

```
Default icon size:  24px
Small (dense):      20px
Large (display):    40px
XL (hero):          48px
```

Icon weight adjusts with font weight for dynamic optical alignment. Use `font-variation-settings` for Material Symbols.

---

## 🌐 Responsive Behavior

| Pattern | Behavior |
|---------|----------|
| Navigation | Bottom nav (mobile) → Rail (tablet) → Drawer (desktop) |
| Cards | Single column → 2-col grid → 3–4 col grid |
| FAB | Full extended → Icon only (scrolled) |
| Dialogs | Full-screen sheet (mobile) → Modal (tablet+) |
| App Bar | Compact (mobile) → Medium/Large (desktop) |

---

## 🌓 Dark Mode Implementation

### CSS Class Strategy

```css
/* Apply to <html> or <body> */
[data-theme="light"] { /* light tokens */ }
[data-theme="dark"]  { /* dark tokens */ }

/* Or use system preference */
@media (prefers-color-scheme: dark) {
  :root { /* dark tokens */ }
}
```

### Transition

```css
* {
  transition: background-color var(--duration-200) var(--easing-standard),
              color var(--duration-200) var(--easing-standard),
              border-color var(--duration-200) var(--easing-standard);
}
```

### Dark Mode Checklist

- [ ] All backgrounds use token variables
- [ ] Images have reduced brightness: `filter: brightness(0.85)`
- [ ] Shadows replaced with tonal color overlays
- [ ] Brand colors use dark-adjusted variants (never raw hex)
- [ ] High-saturation colors muted for dark surfaces
- [ ] `prefers-color-scheme` media query respected

---

## 📋 Design Token Quick Reference

```css
:root {
  /* Use light defaults, override in [data-theme="dark"] */

  /* --- LIGHT (DEFAULT) --- */
  --bg:            #FFFFFF;
  --bg-surface:    #F8F9FA;
  --text:          #202124;
  --text-muted:    #5F6368;
  --border:        #DADCE0;
  --primary:       #1A73E8;
  --primary-text:  #FFFFFF;
  --error:         #D93025;
  --success:       #1E8E3E;
  --warning:       #F9AB00;
}

[data-theme="dark"] {
  --bg:            #202124;
  --bg-surface:    #292A2D;
  --text:          #E8EAED;
  --text-muted:    #9AA0A6;
  --border:        #3C4043;
  --primary:       #8AB4F8;
  --primary-text:  #002884;
  --error:         #F28B82;
  --success:       #81C995;
  --warning:       #FDD663;
}
```

---

*This document follows Google's Material Design 3 guidelines and internal brand standards. For the full component library and Figma kit, refer to [material.io](https://material.io).*
