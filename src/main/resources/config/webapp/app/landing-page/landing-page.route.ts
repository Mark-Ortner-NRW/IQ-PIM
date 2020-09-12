import { Route } from '@angular/router';

import { UserRouteAccessService } from '../core/auth/user-route-access-service';
import { LandingPageComponent } from './landing-page.component';

export const LANDING_PAGE_ROUTE: Route = {
  path: 'landing-page',
  component: LandingPageComponent,
  data: {
    authorities: [],
    pageTitle: 'landing-page.title',
  },
  canActivate: [UserRouteAccessService],
};
