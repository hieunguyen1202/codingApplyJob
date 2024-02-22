import { Component } from '@angular/core';
import { APPROVE_JOB, MANAGE_JOB, PENDING_JOB, REFUSED_JOB } from '../../../../constant';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
})
export class DashboardComponent {
  isCollapsed = false;
  manage_job = MANAGE_JOB;
  pending_job = PENDING_JOB;
  refuse_job = REFUSED_JOB;
  approve_job = APPROVE_JOB;
  defaultComponent = this.manage_job;

  toggleCollapsed(): void {
    this.isCollapsed = !this.isCollapsed;
  }

  manageJob(): void {
    this.defaultComponent = this.manage_job
  }

  pendingJob() : void {
    this.defaultComponent = this.pending_job
  }

  approveJob() : void {
    this.defaultComponent =  this.approve_job
  }

  refuseJob() : void {
    this.defaultComponent = this.refuse_job
  }
}
