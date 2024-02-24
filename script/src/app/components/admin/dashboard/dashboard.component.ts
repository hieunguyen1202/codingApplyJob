import { Component } from '@angular/core';
import {
  APPROVE_JOB,
  JOB_CATEGORY,
  MANAGE_JOB,
  PENDING_JOB,
  REFUSED_JOB,
} from '../../../../constant';

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
  job_category = JOB_CATEGORY;
  defaultComponent = this.job_category;

  toggleCollapsed(): void {
    this.isCollapsed = !this.isCollapsed;
  }

  manageJob(event : Event): void {
    event.stopPropagation()
    this.defaultComponent = this.manage_job;
  }

  jobCategory(event : Event): void {
    event.stopPropagation()
    this.defaultComponent = this.job_category;
  }

  pendingJob(event: Event): void {
    event.stopPropagation();
    this.defaultComponent = this.pending_job;
  }
  
  approveJob(event: Event): void {
    event.stopPropagation();
    this.defaultComponent = this.approve_job;
  }
  
  refuseJob(event: Event): void {
    event.stopPropagation();
    this.defaultComponent = this.refuse_job;
  }
  
}
